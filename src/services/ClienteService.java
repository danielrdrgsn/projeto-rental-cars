package services;

import entities.usuario.Cliente;
import entities.usuario.PessoaFisica;
import entities.usuario.PessoaJuridica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClienteService {
    private static final List<Cliente> clientes = new ArrayList<>();

    public static void adicionar(Scanner input) {
        System.out.println("Digite o nome do cliente:");
        String nome = input.nextLine();

        System.out.println("Digite o email do cliente:");
        String email = input.nextLine();

        System.out.println("Tipo de cliente (1 - Pessoa Física, 2 - Pessoa Jurídica):");
        int tipo = input.nextInt();
        input.nextLine();

        System.out.println("Digite o código do cliente:");
        Integer codigo = input.nextInt();
        input.nextLine();

        if (tipo == 1) {
            System.out.println("Digite o CPF do cliente:");
            String cpf = input.nextLine();
            PessoaFisica pf = new PessoaFisica(codigo, nome, email, codigo, cpf);
            clientes.add(pf);
        } else if (tipo == 2) {
            System.out.println("Digite o CNPJ do cliente:");
            String cnpj = input.nextLine();
            PessoaJuridica pj = new PessoaJuridica(codigo, nome, email, codigo, cnpj);
            clientes.add(pj);
        }
        System.out.println("Cliente adicionado com sucesso!");
    }

    public static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Lista de Clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    public static Cliente buscarCliente(Integer codigo) {
        for (Cliente cliente : clientes) {
            if (cliente.getIdCliente().equals(codigo)) {
                System.out.println("Cliente encontrado: " + cliente);
                return cliente;
            }
        }
        System.out.println("Cliente não encontrado.");
        return null;
    }

    public static void editar(Scanner input) {
        System.out.println("Digite o código do cliente a ser editado:");
        Integer codigo = input.nextInt();
        input.nextLine();

        Cliente cliente = buscarCliente(codigo);
        if (cliente != null) {
            System.out.println("Digite o novo nome do cliente:");
            String novoNome = input.nextLine();
            cliente.setNome(novoNome);
            System.out.println("Cliente editado com sucesso!");
        }
    }

    public static void remover(Scanner input) {
        System.out.println("Digite o código do cliente a ser removido:");
        Integer codigo = input.nextInt();
        Cliente clienteRemovido = buscarCliente(codigo);
        if (clienteRemovido != null) {
            clientes.remove(clienteRemovido);
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
