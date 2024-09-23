package services;

import entities.locadora.Locadora;
import entities.usuario.Administrador;
import entities.usuario.PessoaFisica;
import entities.usuario.PessoaJuridica;
import entities.usuario.Usuario;
import repositories.UsuarioRepository;
import utils.ConsoleColors;

import java.util.List;
import java.util.Scanner;

public class UsuarioService {

    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public static void adicionar(Scanner input) {

        System.out.println("Digite o tipo de cliente que deseja adicionar: ");
        System.out.println("1. Pessoa Física (PF)");
        System.out.println("2. Pessoa Jurídica (PJ)");
        System.out.println("3. Operador/Administrador");
        System.out.println("4. Sair");

        int opcao = input.nextInt();
        input.nextLine();

        if(opcao == 4) {
            return;
        }

        System.out.println("Digite o nome do usuario: ");
        String nome = input.nextLine();
        System.out.println("Digite o email do usuário: ");
        String email = input.nextLine();

        switch (opcao) {
            case 1 -> adicionarPessoaFisica(input, email, nome);
            case 2 -> adicionarPessoaJuridica(input, email, nome);
            case 3 -> adicionarAdministrador(input, email, nome);
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void editar() {
        // TODO
    }

    public static void buscar(Scanner input) {
        System.out.println("Digite o e-mail do usuario: ");
        String email  = input.nextLine(); // TODO: validação de email?

        Usuario usuario = usuarioRepository.buscar(email);

        if(usuario instanceof Administrador administrador) {
            System.out.println("\n" + administrador.mostrarAdmin());
        } else if (usuario instanceof PessoaFisica pessoaFisica) {
            System.out.println("\n" + pessoaFisica.mostrarPF());
        } else if (usuario instanceof PessoaJuridica pessoaJuridica) {
            System.out.println("\n" + pessoaJuridica.mostrarPJ());;
        } else {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nUsuário não encontrado.\n" + ConsoleColors.RESET);
        }
    }

    public static void remover(Scanner input) {
        System.out.println("Digite o e-mail do usuario: ");
        String email  = input.nextLine(); // TODO: validação de email?

        Usuario usuario = usuarioRepository.buscar(email);
        usuario = usuarioRepository.remover(usuario);

        if(usuario != null) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void listar() {
        List<Usuario> usuarios = usuarioRepository.listar();
        for(Usuario usuario : usuarios) {
            System.out.println(usuario.mostrarUsuario());
        }
    }

    private static void adicionarAdministrador(Scanner input, String email, String nome) {
        System.out.println("Digite o número de registro do funcionário: ");
        Integer numeroRegistro = input.nextInt(); // TODO: verificar/validar o input

        if (!usuarioEncontrado(email)) {
            Administrador adm = new Administrador(nome, email, numeroRegistro);
            usuarioRepository.adicionar(adm);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuario adicionado com sucesso!"+ ConsoleColors.RESET);
        }
    }

    private static void adicionarPessoaJuridica(Scanner input, String email, String nome) {
        System.out.println("Digite o CNPJ do usuario: ");
        String cnpj = input.nextLine();

        if (!usuarioEncontrado(email)) {
            PessoaJuridica pj = new PessoaJuridica(nome, email, cnpj);
            usuarioRepository.adicionar(pj);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuario adicionado com sucesso!"+ ConsoleColors.RESET);
        }
    }

    private static void adicionarPessoaFisica(Scanner input, String email, String nome) {
        System.out.println("Digite o CPF do usuario: ");
        String cpf = input.nextLine();

        if (!usuarioEncontrado(email)) {
            PessoaFisica pf = new PessoaFisica(nome, email, cpf);
            usuarioRepository.adicionar(pf);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuario adicionado com sucesso!"+ ConsoleColors.RESET);
        }
    }

    private static boolean usuarioEncontrado(String email) {
        boolean encontrado = false;
        for (Usuario u : Locadora.getUsuarios()) {
            if (u.getEmail().equals(email)) {
                encontrado = true;
                System.out.println("Usuário/email já cadastrado!");
                break;
            }
        }
        return encontrado;
    }


}
