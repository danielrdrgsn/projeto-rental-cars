package services;

import entities.locadora.Locadora;
import entities.veiculo.Caminhao;
import entities.veiculo.Carro;
import entities.veiculo.Moto;
import entities.veiculo.Veiculo;
import repositories.VeiculoRepository;
import utils.ConsoleColors;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VeiculoService {

    private static final VeiculoRepository veiculoRepository = new VeiculoRepository();

    public static void adicionar(Scanner input) {

        try {
            int opcao = mostrarMenuAdicaoVeiculo(input);
            if (opcao == 4) {
                System.out.println("Operação cancelada.");
                return;
            }

            String placa = solicitarPlacaValida(input);
            String modeloVeiculo = solicitarModelo(input);
            int anoFabricacao = solicitarAnoFabricacao(input);
            String corVeiculo = solicitarCor(input);
            Integer codigoAgencia = solicitarCodigoAgenciaValido(input);

            Integer idVeiculo = obterUltimoIdVeiculo() + 1;

            Veiculo novoVeiculo = criarVeiculo(idVeiculo, opcao, placa, modeloVeiculo, anoFabricacao, corVeiculo, codigoAgencia);
            veiculoRepository.adicionar(novoVeiculo);

            System.out.println(novoVeiculo.getClass().getSimpleName() + " adicionado com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Certifique-se de inserir os dados corretamente.");
            input.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Veiculo criarVeiculo(Integer idVeiculo, int opcao, String placa, String modeloVeiculo, int anoFabricacao, String corVeiculo, Integer codigoAgencia) {
        return switch (opcao) {
            case 1 -> new Carro(idVeiculo, placa, modeloVeiculo, anoFabricacao, corVeiculo, true, codigoAgencia);
            case 2 -> new Moto(idVeiculo, placa, modeloVeiculo, anoFabricacao, corVeiculo, true, codigoAgencia);
            case 3 -> new Caminhao(idVeiculo, placa, modeloVeiculo, anoFabricacao, corVeiculo, true, codigoAgencia);
            default -> throw new IllegalArgumentException("Tipo de veículo desconhecido.");
        };
    }

    private static int mostrarMenuAdicaoVeiculo(Scanner input) {
        int opcao = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.println("Digite o tipo de veículo que deseja cadastrar: ");
                System.out.println("1. Carro");
                System.out.println("2. Moto");
                System.out.println("3. Caminhão");
                System.out.println("4. Voltar");

                opcao = input.nextInt();
                input.nextLine();

                if (opcao >= 1 && opcao <= 4) {
                    inputValido = true;
                } else {
                    System.out.println("Opção inválida. Digite um número entre 1 e 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                input.nextLine();
            }
        }
        return opcao;
    }

    private static String solicitarPlacaValida(Scanner input) {
        String placa;
        Veiculo veiculo;

        do {
            System.out.println("Digite a placa do veículo:");
            placa = input.nextLine().trim();

            veiculo = veiculoRepository.buscar(placa);
            if (veiculo != null) {
                System.out.println("Placa já utilizada por outro veículo. Tente novamente.");
            }
        } while (veiculo != null);

        return placa;
    }

    private static String solicitarModelo(Scanner input) {
        System.out.println("Digite o modelo do veículo:");
        return input.nextLine().trim();
    }

    private static int solicitarAnoFabricacao(Scanner input) {
        int anoFabricacao = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.println("Digite o ano de fabricação do veículo:");
                anoFabricacao = input.nextInt();
                input.nextLine();

                if (anoFabricacao <= java.time.Year.now().getValue()) {
                    inputValido = true;
                } else {
                    System.out.println("Ano de fabricação inválido.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um ano válido.");
                input.nextLine();
            }
        }
        return anoFabricacao;
    }

    private static String solicitarCor(Scanner input) {
        System.out.println("Digite a cor do veículo:");
        return input.nextLine().trim();
    }

    private static Integer solicitarCodigoAgenciaValido(Scanner input) {
        Integer codigoAgencia = null;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.println("Digite o código da agência de origem do veículo:");
                codigoAgencia = input.nextInt();
                input.nextLine();

                if (AgenciaService.buscarAgencia(codigoAgencia) != null) {
                    inputValido = true;
                } else {
                    System.out.println("Código de agência inválido. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                input.nextLine();
            }
        }
        return codigoAgencia;
    }

    public static void editar(Scanner input) {
        try {
            System.out.println("Digite a placa atual do veículo:");
            String placaAtual = input.nextLine().trim();

            Veiculo veiculo = veiculoRepository.buscar(placaAtual);
            if (veiculo == null) {
                System.out.println("Veículo não encontrado.");
                return;
            }

            String novaPlaca = solicitarNovaPlaca(input, placaAtual);
            String novaCor = solicitarNovaCor(input, veiculo.getCor());

            veiculo.setPlaca(novaPlaca.isEmpty() ? placaAtual : novaPlaca);
            veiculo.setCor(novaCor.isEmpty() ? veiculo.getCor() : novaCor);

            veiculoRepository.editar(veiculo, placaAtual);
            System.out.println("Veículo editado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Verifique os dados inseridos.");
            input.nextLine();
        }
    }

    private static String solicitarNovaPlaca(Scanner input, String placaAtual) {
        String novaPlaca;
        Veiculo veiculoExistente;

        do {
            System.out.println("Digite a nova placa para o veículo ou tecle <ENTER> para manter a mesma:");
            novaPlaca = input.nextLine().trim();

            if (novaPlaca.isEmpty()) {
                return placaAtual;
            }

            veiculoExistente = veiculoRepository.buscar(novaPlaca);
            if (veiculoExistente != null && !veiculoExistente.getPlaca().equals(placaAtual)) {
                System.out.println("Placa já utilizada por outro veículo.");
            }
        } while (veiculoExistente != null && !veiculoExistente.getPlaca().equals(placaAtual));

        return novaPlaca;
    }

    private static String solicitarNovaCor(Scanner input, String corAtual) {
        System.out.println("Digite a nova cor ou tecle <ENTER> para manter a mesma:");
        String novaCor = input.nextLine().trim();

        return novaCor.isEmpty() ? corAtual : novaCor;
    }

    public static void alteraDisponibilidade(Veiculo veiculo) {
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
        if (veiculo != null) {
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void listar(Scanner input) {
        List<Veiculo> veiculos = veiculoRepository.listar();

        int tamanhoPagina = 2; // quantidade de veiculos por página
        int totalPaginas = (int) Math.ceil((double) veiculos.size() / tamanhoPagina);
        int paginaAtual = 1;

        while (true) {
            exibirPaginaVeiculos(veiculos, paginaAtual, tamanhoPagina);
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
            System.out.println(veiculos.get(i).mostrarVeiculo());
        }
    }

    public static void buscarVeiculosPorModelo(Scanner input) {
        System.out.println("Digite o modelo do veículo: ");
        String modelo = input.nextLine();

        List<Veiculo> veiculos = veiculoRepository.buscarPorModelo(modelo);
        Collections.sort(veiculos);
        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.mostrarVeiculo());
        }
    }

    public static Veiculo buscarVeiculo(String placa) {
        return veiculoRepository.buscar(placa);
    }

    public static Veiculo buscarVeiculo(Integer codigo) {
        return veiculoRepository.buscarPorId(codigo);
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
