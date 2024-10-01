package services;

import entities.agencia.Agencia;
import entities.agencia.Endereco;
import repositories.AgenciaRepository;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AgenciaService {

    private static final AgenciaRepository agenciaRepository = new AgenciaRepository();

    public static void listarAgencias() {
        List<Agencia> agencias = agenciaRepository.listar();
        if (agencias.isEmpty()) {
            System.out.println("Nenhuma agência cadastrada.");
        } else {
            System.out.println("==============================================");
            System.out.println("|             Lista de Agências              |");
            System.out.println("==============================================");
            for (Agencia agencia : agencias) {
                System.out.println("| Código: " + agencia.getCodigo());
                System.out.println("| Nome: " + agencia.getNome());
                System.out.println("| Endereço: ");
                System.out.println(agencia.getEndereco().mostrarEndereco());
                System.out.println("==============================================");
            }
        }
    }

    public static void buscarAgencia(Scanner input) {
        System.out.println("Digite o código da agência:");
        Integer codigo = input.nextInt();
        input.nextLine();
        Agencia agencia = agenciaRepository.buscar(codigo);
        if (agencia != null) {
            System.out.println("==============================================");
            System.out.println("|            Agência Encontrada              |");
            System.out.println("==============================================");
            System.out.println("| Código: " + agencia.getCodigo());
            System.out.println("| Nome: " + agencia.getNome());
            System.out.println("| Endereço: ");
            System.out.println(agencia.getEndereco().mostrarEndereco());
            System.out.println("==============================================");
        } else {
            System.out.println("Agência não encontrada.");
        }
    }

    public static void buscarAgenciaPorParteDoNome(Scanner input){
        System.out.println("Digite uma parte do nome da agência: ");
        String parteNome = input.nextLine();

        List<Agencia> agencias = AgenciaRepository.buscarPorParteDoNome(parteNome);

        for(Agencia agencia : agencias){
            System.out.println(agencia.mostrarAgencia());
            System.out.println("==============================================");
        }
    }

    public static Agencia buscarAgencia(Integer codigo) {
        return agenciaRepository.buscar(codigo);
    }

    public static void adicionarAgencia(Scanner input) {
        System.out.println("Digite o código da agência:");
        Integer codigo = input.nextInt();
        input.nextLine();

        System.out.println("Digite o nome da agência:");
        String nome = input.nextLine();
        System.out.println("Digite o logradouro do endereço:");
        String logradouro = input.nextLine();
        System.out.println("Digite o número:");
        Integer numero = input.nextInt();
        input.nextLine();
        System.out.println("Digite o complemento:");
        String complemento = input.nextLine();
        System.out.println("Digite a cidade:");
        String cidade = input.nextLine();
        System.out.println("Digite o estado:");
        String estado = input.nextLine();
        System.out.println("Digite o CEP:");
        String cep = input.nextLine();

        Endereco endereco = new Endereco(logradouro, numero, complemento, cidade, estado, cep);
        Agencia agencia = new Agencia(codigo, nome, endereco);

        if (agenciaRepository.existeAgenciaComMesmosDados(agencia)) {
            System.out.println("Agência já cadastrada com esses dados. Tente novamente com dados diferentes.");
            return;
        }

        agenciaRepository.adicionar(agencia);
        System.out.println("Agência adicionada com sucesso!");
    }

    public static void editarAgencia(Scanner input) {
        System.out.println("Digite o código da agência a ser editada:");
        Integer codigo = input.nextInt();
        input.nextLine();
        Agencia agencia = agenciaRepository.buscar(codigo);

        if (agencia == null) {
            System.out.println("Agência não encontrada");
            return;
        }

        Agencia original = new Agencia(agencia.getCodigo(), agencia.getNome(), agencia.getEndereco());

        System.out.println("Digite o novo nome da agência ou tecle <ENTER> para manter o atual:");
        String novoNome = input.nextLine();
        agencia.setNome(novoNome.isEmpty() ? agencia.getNome() : novoNome);

        System.out.println("Deseja alterar o endereço? (S/N)");
        String opcao = input.nextLine();

        if (opcao.equalsIgnoreCase("S")) {
            System.out.println("Digite o novo logradouro do endereço:");
            String novoLogradouro = input.nextLine();
            agencia.getEndereco().setLogradouro(novoLogradouro);

            System.out.println("Digite o novo número:");
            Integer novoNumero = input.nextInt();
            agencia.getEndereco().setNumero(novoNumero);
            input.nextLine();

            System.out.println("Digite o novo complemento:");
            String novoComplemento = input.nextLine();
            agencia.getEndereco().setComplemento(novoComplemento);

            System.out.println("Digite a nova cidade:");
            String novaCidade = input.nextLine();
            agencia.getEndereco().setCidade(novaCidade);

            System.out.println("Digite o novo estado:");
            String novoEstado = input.nextLine();
            agencia.getEndereco().setEstado(novoEstado);

            System.out.println("Digite o novo CEP:");
            String novoCep = input.nextLine();
            agencia.getEndereco().setCep(novoCep);
        }

        if (agencia.equals(original)) {
            System.out.println("Nenhuma alteração foi feita.");
        } else if (agenciaRepository.existeAgenciaComMesmosDados(agencia)) {
            System.out.println("Já existe uma agência com esses dados no sistema.");
        } else {
            agenciaRepository.editar(agencia, codigo);
            System.out.println("Agência editada com sucesso!");
        }
    }

    public static void removerAgencia(Scanner input) {
        System.out.println("Digite o código da agência que deseja remover:");
        Integer codigo = input.nextInt();

        for (Agencia agencia : agenciaRepository.listar()) {
            if (Objects.equals(agencia.getCodigo(), codigo)) {
                try {
                    agenciaRepository.remover(agencia);
                    System.out.println("Agência removida com sucesso.");
                    LocadoraUtils.salvarDadosLocadora();
                    break;
                } catch (IOException e) {
                    throw new RuntimeException("Erro ao salvar os dados: " + e.getMessage());
                }
            }
        }
        System.out.println("Agência não encontrada.");
    }
}