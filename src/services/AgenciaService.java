package services;

import entities.agencia.Agencia;
import entities.agencia.Endereco;
import repositories.AgenciaRepository;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class AgenciaService {

    private static final AgenciaRepository agenciaRepository = new AgenciaRepository();

    public static void listarAgencias() {
        var agencias = agenciaRepository.listar();
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
        String numero = input.nextLine();
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

        agenciaRepository.adicionar(agencia);
    }

    public static void editarAgencia(Scanner input) {
        System.out.println("Digite o código da agência a ser editada:");
        Integer codigo = input.nextInt();
        input.nextLine();


        Agencia agencia = agenciaRepository.buscar(codigo);
        if (agencia != null) {
            System.out.println("Digite o novo nome da agência:");
            String novoNome = input.nextLine();
            agencia.setNome(novoNome);

            System.out.println("Deseja alterar o endereço? (S/N)");
            String alterarEndereco = input.nextLine();

            if (alterarEndereco.equalsIgnoreCase("S")) {
                System.out.println("Digite o novo logradouro do endereço:");
                String novoLogradouro = input.nextLine();
                agencia.getEndereco().setLogradouro(novoLogradouro);

                System.out.println("Digite o novo número:");
                String novoNumero = input.nextLine();
                agencia.getEndereco().setNumero(novoNumero);

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

            agenciaRepository.editar(agencia, codigo);

            try {
                LocadoraUtils.salvarDadosLocadora();
            } catch (IOException e) {
                System.out.println("Erro ao salvar os dados: " + e.getMessage());
            }
        } else {
            System.out.println("Agência não encontrada.");
        }
    }

    public static void removerAgencia(Scanner input) {
        System.out.println("Digite o código da agência que deseja remover:");
        Integer codigo = input.nextInt();

        for(Agencia agencia : agenciaRepository.listar()) {
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