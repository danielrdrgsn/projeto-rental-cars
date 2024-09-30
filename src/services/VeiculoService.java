package services;

import entities.locadora.Locadora;
import entities.veiculo.*;
import repositories.VeiculoRepository;
import utils.ConsoleColors;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class VeiculoService {

    private static VeiculoRepository veiculoRepository;

    public static void adicionar(Scanner input) {
        System.out.println("Digite a placa do veiculo: ");
        String placaVeiculo = input.nextLine();

        System.out.println("Digite o modelo do veiculo: ");
        String modeloVeiculo = input.nextLine();

        System.out.println("Digite o ano do veiculo: ");
        int anoFabricacao = input.nextInt();
        input.nextLine();

        System.out.println("Digite a cor do veiculo: ");
        String corVeiculo = input.nextLine();

        System.out.println("Qual tipo de Veiculo você gostaria:");
        for (int i = 0; i < TipoVeiculo.values().length; i++) {
            System.out.println((i + 1) + ". " + TipoVeiculo.values()[i].getDescricao());
        }
        int escolhaVeiculo = input.nextInt();
        input.nextLine();

        Integer idVeiculo = obterUltimoIdVeiculo() + 1;

        switch (escolhaVeiculo) {
            case 1 -> {
                veiculoRepository.adicionar(new Carro(idVeiculo, placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, true));
                System.out.println("Carro cadastrado com sucesso!");
            }
            case 2 -> {
                veiculoRepository.adicionar(new Moto(idVeiculo, placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, true));
                System.out.println("Moto cadastrada com sucesso!");
            }
            case 3 -> {
                veiculoRepository.adicionar(new Caminhao(idVeiculo, placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, true));
                System.out.println("Caminhão cadastrado com sucesso!");
            }
            default -> System.out.println("Tipo de veículo desconhecido.");
        }
    }

    public static void editar(Scanner input) {
        System.out.println("Qual o placa do veiculo que você quer atualizar?");
        String placaAtual = input.nextLine();
        Veiculo veiculo = veiculoRepository.buscar(placaAtual);

        if(veiculo == null) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nVeículo não encontrado.\n" + ConsoleColors.RESET);
            return;
        }

        System.out.println("Digite a nova placa para o veículo ou tecle <ENTER> para manter a mesma: ");
        String novaPlaca = input.nextLine();

        Veiculo veiculoExistente = veiculoRepository.buscar(novaPlaca);
        while(veiculoExistente != null && !veiculoExistente.getPlaca().equals(placaAtual)) {
            System.out.println("Placa já utilizada por outro veículo.");
            System.out.println("Digite a nova placa para o veículo ou tecle <ENTER> para manter a mesma: ");
            novaPlaca = input.nextLine();
            veiculoExistente = veiculoRepository.buscar(novaPlaca);
        }
        veiculo.setPlaca(novaPlaca);

        System.out.println("Digite a nova cor ou tecle <ENTER> para manter a mesma: ");
        String novaCor = input.nextLine();
        veiculo.setCor(novaCor.isEmpty() ? veiculo.getCor() : novaCor);

        veiculoRepository.editar(veiculo, placaAtual);
        System.out.println("Veículo editado com sucesso!");
    }

    public void alteraDisponibilidade(Veiculo veiculo) {
        veiculo.setDisponivel(!veiculo.isDisponivel());
    }

    public static void buscar(Scanner input) {
        System.out.println("Qual veiculo que voce quer buscar? digite a placa");
        String placaVeiculo = input.nextLine();

        Veiculo veiculo = veiculoRepository.buscar(placaVeiculo);

        if (veiculo != null) {
            System.out.println(veiculo.mostrarVeiculo());
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void remover(Scanner input) {
        System.out.println("Qual o veiculo que voce quer remover? digite a placa");
        String placaVeiculo = input.nextLine();

        Veiculo veiculo = veiculoRepository.buscar(placaVeiculo);
        veiculo = veiculoRepository.remover(veiculo);
        if(veiculo != null) {
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void listar(Scanner input) {
        List<Veiculo> veiculos = veiculoRepository.listar();
        Collections.sort(veiculos);

        int tamanhoPagina = 2; // quantidade de veiculos por página
        int totalPaginas = (int) Math.ceil((double) veiculos.size() / tamanhoPagina);
        int paginaAtual = 1;

        while(true) {
            exibirPaginaVeiculos(veiculos, paginaAtual, totalPaginas);
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

    public static void buscarVeiculosPorModelo(Scanner input) {
        System.out.println("Digite o modelo do veículo: ");
        String modelo = input.nextLine();

        List<Veiculo> veiculos = veiculoRepository.buscarPorModelo(modelo);
        Collections.sort(veiculos);
        for(Veiculo veiculo : veiculos) {
            System.out.println(veiculo.mostrarVeiculo());
        }
    }

    public static Veiculo buscarVeiculo(String placa) {
        return veiculoRepository.buscar(placa);
    }

    public static Veiculo buscarVeiculo(Integer codigo) {
        return veiculoRepository.buscarPorId(codigo);
    }

    protected static void exibirPaginaVeiculos(List<Veiculo> veiculos, int pagina, int tamanhoPagina) {
        int inicio = (pagina - 1) * tamanhoPagina;
        int fim = Math.min(inicio + tamanhoPagina, veiculos.size());

        for (int i = inicio; i < fim; i++) {
            veiculos.get(i).mostrarVeiculo();
        }
    }

    private static Integer obterUltimoIdVeiculo() {
        Integer ultimoIdVeiculo = -1;
        for (Veiculo v : Locadora.getVeiculos()) {
            if (v.getId() > ultimoIdVeiculo) {
                ultimoIdVeiculo = v.getId();
            }
        }
        return ultimoIdVeiculo;
    }

    public static List<Veiculo> buscarVeiculosDisponiveis() {
        return veiculoRepository.bucarVeiculosDisponiveis();
    }

}
