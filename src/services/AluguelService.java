package services;

import entities.agencia.Agencia;
import entities.locadora.Aluguel;
import entities.locadora.Locadora;
import entities.usuario.Cliente;
import entities.usuario.Usuario;
import entities.veiculo.Veiculo;
import repositories.AluguelRepositoryImplementacao;
import utils.ConsoleColors;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static services.VeiculoService.*;

public class AluguelService {

    private static AluguelRepositoryImplementacao aluguelRepository;


    public static void alugarVeiculo(Scanner input, Usuario usuario) {
        Cliente cliente = (Cliente) usuario;

        System.out.println("Escolha o ID do veículo:");
        mostrarVeiculosDisponiveis(input);

        System.out.println("ID escolhido: ");
        Integer idVeiculo = input.nextInt();
        input.nextLine();
        Veiculo escolhido = buscarVeiculo(idVeiculo); // TODO: validar escolha
        escolhido.setDisponivel(false);

        Agencia localRetirada = AgenciaService.buscarAgencia(escolhido.getCodAgenciaAtual());

        Integer novoIdAluguel = obterUltimoIdAluguel() + 1;

        Aluguel novoAluguel = new Aluguel(novoIdAluguel, cliente, escolhido, LocalDateTime.now(), localRetirada);


        aluguelRepository.salvarAluguel(novoAluguel);
        System.out.println(gerarComprovanteDeAluguel(novoAluguel));
        System.out.println("Aluguel salvo com sucesso.");
    }

    public static void devolverVeiculo(Scanner input, Usuario usuario) {
        Cliente cliente = (Cliente) usuario;

        System.out.println("Digite a placa do veículo que está sendo devolvido: ");
        String placa = input.nextLine();
        Veiculo devolvido = buscarVeiculo(placa);
        if(devolvido == null) {
            System.out.println("Veículo não encontrado.");
        } else {
            List<Aluguel> alugueis = aluguelRepository.buscarPorCliente(cliente);
            for(Aluguel aluguel : alugueis) {
                if(aluguel.getCliente().equals(cliente)) {
                    devolvido.setDisponivel(true);
                    System.out.println(gerarComprovanteDeDevolucao(aluguel));
                    break;
                }
            }
            System.out.println("Veículo devolvido com sucesso.");
        }
    }

    private static Integer obterUltimoIdAluguel() {
        Integer ultimoId = -1;
        for(Aluguel a: Locadora.getAlugueis()) {
            if(a.getId() > ultimoId) {
                ultimoId = a.getId();
            }
        }
        return ultimoId;
    }

    private static void mostrarVeiculosDisponiveis(Scanner input) {
        List<Veiculo> disponiveis = buscarVeiculosDisponiveis();

        int tamanhoPagina = 2; // quantidade de itens por página
        int totalPaginas = (int) Math.ceil((double) disponiveis.size() / tamanhoPagina);
        int paginaAtual = 1;

        while (true) {
            exibirPaginaVeiculos(disponiveis, paginaAtual, tamanhoPagina);
            System.out.println("\nPágina " + paginaAtual + " de " + totalPaginas);
            System.out.println("[P] Próxima página | [A] Página anterior | [S] Sair");

            String opcao = input.nextLine().toUpperCase();
            if (opcao.equals("P") && paginaAtual < totalPaginas) {
                paginaAtual++;
            } else if (opcao.equals("A") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("S")) {
                break;
            } else {
                System.out.println(ConsoleColors.RED + "Comando inválido!" + ConsoleColors.RESET);
            }
        }
    }

    private static void exibirPaginaVeiculos(List<Veiculo> veiculos, int pagina, int tamanhoPagina) {
        int inicio = (pagina - 1) * tamanhoPagina;
        int fim = Math.min(inicio + tamanhoPagina, veiculos.size());

        for (int i = inicio; i < fim; i++) {
            veiculos.get(i).mostrarVeiculo();
        }
    }

    public List<Aluguel> listarAlugueis() {
        return aluguelRepository.buscarTodos();
    }

    public static void buscarAlugueisPorCliente(Cliente cliente) {
        List<Aluguel> alugueis = aluguelRepository.buscarPorCliente(cliente);
    }

    public List<Aluguel> buscarAlugueisPorVeiculo(Veiculo veiculo) {
        return aluguelRepository.buscarPorVeiculo(veiculo);
    }

    public static void mostrarComprovanteDevolucoes(Scanner input, Cliente cliente) {
        List<Aluguel> alugueis = aluguelRepository.buscarPorCliente(cliente);
        for (Aluguel aluguel : alugueis) {
            System.out.println(gerarComprovanteDeDevolucao(aluguel));
        }
    }

    public static String gerarComprovanteDeAluguel(Aluguel aluguel) {
        return "|> Comprovante de Aluguel: \n"
                + "Cliente: " + aluguel.getCliente().getNome() + "\n"
                + "Veículo: " + aluguel.getVeiculo().getModelo() + "\n"
                + "Data de retirada: " + aluguel.getDataRetirada() + "\n"
                + "Local de retirada: " + aluguel.getLocalRetirada().getNome() + "\n";
    }

    public static String gerarComprovanteDeDevolucao(Aluguel aluguel) {
        return "|> Comprovante de Devolução: \n"
                + "ID: " + aluguel.getId() + "\n"
                + "Cliente: " + aluguel.getCliente().getNome() + "\n"
                + "Veículo: " + aluguel.getVeiculo().getModelo() + "\n"
                + "Data do Aluguel: " + aluguel.getDataRetirada() + "\n"
                + "Data de Devolução: " + aluguel.getDataDevolucao() + "\n"
                + "Local de Devolução: " + aluguel.getLocalDevolucao().getNome() + "\n"
                + "Total de Dias Alugados: " + aluguel.calcularDiasAlugados() + "dias" + "\n"
                + "Valor total do aluguel: " + aluguel.calcularValorTotal() + "\n";
    }
}