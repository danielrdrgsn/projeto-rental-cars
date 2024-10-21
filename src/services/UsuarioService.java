package services;

import entities.usuario.Administrador;
import entities.usuario.Cliente;
import entities.usuario.PessoaFisica;
import entities.usuario.PessoaJuridica;
import entities.usuario.Usuario;
import repositories.UsuarioRepository;
import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UsuarioService {

    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public static void adicionar(Scanner input) {
        int opcao = mostrarMenuDeAdicao(input);
        input.nextLine();

        if (opcao == 4) return;

        System.out.println("Digite o nome do usuario: ");
        String nome = input.nextLine();
        System.out.println("Digite o email do usuário: ");
        String email = input.nextLine();

        int novoIdUsuario = obterUltimoIdUsuario() + 1;
        int novoIdCliente = obterUltimoIdCliente() + 1;

        adicionarUsuarioPorTipo(opcao, input, novoIdUsuario, email, nome, novoIdCliente);
    }

    private static void adicionarUsuarioPorTipo(int opcao, Scanner input, int idUsuario, String email, String nome, int idCliente) {
        switch (opcao) {
            case 1 -> adicionarPessoaFisica(input, idUsuario, email, nome, idCliente);
            case 2 -> adicionarPessoaJuridica(input, idUsuario, email, nome, idCliente);
            case 3 -> adicionarAdministrador(input, idUsuario, email, nome);
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void adicionarAdministrador(Scanner input, int idUsuario, String email, String nome) {
        if (emailExistente(email)) {
            exibirMensagemEmailJaCadastrado();
            return;
        }

        System.out.println("Digite o número de registro do funcionário: ");
        Integer numeroRegistro = lerInteiro(input);

        Administrador adm = new Administrador(idUsuario, nome, email, numeroRegistro);
        usuarioRepository.adicionar(adm);
        exibirMensagemSucesso(nome);
    }

    private static void adicionarPessoaJuridica(Scanner input, int idUsuario, String email, String nome, int idCliente) {
        if (emailExistente(email)) {
            exibirMensagemEmailJaCadastrado();
            return;
        }

        System.out.println("Digite o CNPJ do usuário: ");
        String cnpj = lerTextoNaoVazio(input);

        PessoaJuridica pj = new PessoaJuridica(idUsuario, nome, email, idCliente, cnpj);
        usuarioRepository.adicionar(pj);
        exibirMensagemSucesso(nome);
    }

    private static void adicionarPessoaFisica(Scanner input, int idUsuario, String email, String nome, int idCliente) {
        if (emailExistente(email)) {
            exibirMensagemEmailJaCadastrado();
            return;
        }

        System.out.println("Digite o CPF do usuário: ");
        String cpf = lerTextoNaoVazio(input);

        PessoaFisica pf = new PessoaFisica(idUsuario, nome, email, idCliente, cpf);
        usuarioRepository.adicionar(pf);
        exibirMensagemSucesso(nome);
    }

    private static boolean emailExistente(String email) {
        return usuarioRepository.buscar(email).isPresent();
    }

    public static void editar(Scanner input) {
        System.out.println("Digite o email do usuário que deseja alterar dados: ");
        String email = input.nextLine();

        usuarioRepository.buscar(email).ifPresentOrElse(usuario -> {
            atualizarEmail(usuario, input);
            atualizarNome(usuario, input);

            if (usuario instanceof Administrador adm) {
                atualizarNumeroRegistro(adm, input);
            } else if (usuario instanceof PessoaFisica pf) {
                atualizarCpf(pf, input);
            } else if (usuario instanceof PessoaJuridica pj) {
                atualizarCnpj(pj, input);
            }

            usuarioRepository.editar(usuario, email);
        }, () -> System.out.println("Usuário não encontrado."));
    }

    private static void atualizarEmail(Usuario usuario, Scanner input) {
        System.out.println("Digite um novo email ou tecle <ENTER> para manter o mesmo: ");
        String novoEmail = input.nextLine();

        if (!novoEmail.isEmpty() && !emailExistente(novoEmail)) {
            usuario.setEmail(novoEmail);
        }
    }

    private static void atualizarNome(Usuario usuario, Scanner input) {
        System.out.println("Digite o novo nome ou tecle <ENTER> para manter o mesmo: ");
        String novoNome = input.nextLine();

        if (!novoNome.isEmpty()) {
            usuario.setNome(novoNome);
        }
    }

    private static void atualizarNumeroRegistro(Administrador adm, Scanner input) {
        System.out.println("Digite o novo número de registro ou <ENTER> para manter o mesmo: ");
        String entrada = input.nextLine();

        if (!entrada.isEmpty()) {
            adm.setNumeroRegistro(Integer.parseInt(entrada));
        }
    }

    private static void atualizarCpf(PessoaFisica pf, Scanner input) {
        System.out.println("Digite o novo CPF ou <ENTER> para manter o mesmo: ");
        String novoCpf = input.nextLine();

        if (!novoCpf.isEmpty()) {
            pf.setCpf(novoCpf);
        }
    }

    private static void atualizarCnpj(PessoaJuridica pj, Scanner input) {
        System.out.println("Digite o novo CNPJ ou <ENTER> para manter o mesmo: ");
        String novoCnpj = input.nextLine();

        if (!novoCnpj.isEmpty()) {
            pj.setCnpj(novoCnpj);
        }
    }

    public static void listar(Scanner input) {
        List<Usuario> usuarios = usuarioRepository.listar();
        int tamanhoPagina = 2;
        int paginaAtual = 1;
        int totalPaginas = (int) Math.ceil((double) usuarios.size() / tamanhoPagina);

        while (true) {
            exibirPaginaUsuarios(usuarios, paginaAtual, tamanhoPagina);
            System.out.printf("\nPágina %d de %d\n", paginaAtual, totalPaginas);
            System.out.println("[P] Próxima | [A] Anterior | [S] Sair");

            String opcao = input.nextLine().toUpperCase();
            if (opcao.equals("P") && paginaAtual < totalPaginas) paginaAtual++;
            else if (opcao.equals("A") && paginaAtual > 1) paginaAtual--;
            else if (opcao.equals("S")) break;
        }
    }

    private static void exibirPaginaUsuarios(List<Usuario> usuarios, int pagina, int tamanhoPagina) {
        usuarios.stream()
                .skip((pagina - 1L) * tamanhoPagina)
                .limit(tamanhoPagina)
                .forEach(usuario -> System.out.println(usuario.mostrarUsuario()));
    }

    private static int mostrarMenuDeAdicao(Scanner input) {
        System.out.println("1. Pessoa Física\n2. Pessoa Jurídica\n3. Administrador\n4. Sair");
        return input.nextInt();
    }

    private static Integer lerInteiro(Scanner input) {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                input.nextLine();
            }
        }
    }

    private static String lerTextoNaoVazio(Scanner input) {
        String texto;
        do {
            texto = input.nextLine();
            if (texto.trim().isEmpty()) System.out.println("Entrada não pode ser vazia.");
        } while (texto.trim().isEmpty());
        return texto;
    }

    private static void exibirMensagemSucesso(String nome) {
        System.out.println("Usuário " + nome + " adicionado com sucesso!");
    }

    private static void exibirMensagemEmailJaCadastrado() {
        System.out.println("Email já cadastrado.");
    }

    private static int obterUltimoIdUsuario() {
        return usuarioRepository.listar().stream()
                .mapToInt(Usuario::getId)
                .max()
                .orElse(0);
    }

    private static int obterUltimoIdCliente() {
        return usuarioRepository.listar().stream()
                .filter(Cliente.class::isInstance)
                .mapToInt(u -> ((Cliente) u).getIdCliente())
                .max()
                .orElse(0);
    }

    public static void buscar(Scanner input) {
        System.out.println("Digite o email do usuario: ");
        String email = input.nextLine(); // TODO: validação de email?

        Optional<Usuario> usuario = usuarioRepository.buscar(email);

        if (usuario.isPresent() && usuario.get() instanceof Administrador administrador) {
            System.out.println("\n" + administrador.mostrarAdmin());
        } else if (usuario.isPresent() && usuario.get() instanceof PessoaFisica pessoaFisica) {
            System.out.println("\n" + pessoaFisica.mostrarPF());
        } else if (usuario.isPresent() && usuario.get() instanceof PessoaJuridica pessoaJuridica) {
            System.out.println("\n" + pessoaJuridica.mostrarPJ());
        } else {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nUsuário não encontrado.\n" + ConsoleColors.RESET);
        }
    }

    public static Cliente buscarCliente(String email) {
        Optional<Usuario> usuario = usuarioRepository.buscar(email);
        if (usuario.isPresent() && usuario.get() instanceof Cliente cliente) {
            return cliente;
        }
        return null;
    }

    public static void buscarPorParteDoNome(Scanner input){
        System.out.println("Digite uma parte do nome do usuário: ");
        String parteNome = input.nextLine();

        List<Usuario> usuarios = UsuarioRepository.buscarPorParteDoNome(parteNome);

        for(Usuario usuario : usuarios){
            System.out.println(usuario.mostrarUsuario());
        }
    }

    public static void remover(Scanner input) {
        System.out.println("Digite o email do usuário: ");
        String email = input.nextLine();

        Optional<Usuario> usuario = usuarioRepository.buscar(email);

        if (usuario.isPresent()) {
            usuarioRepository.remover(usuario.get());
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }
}