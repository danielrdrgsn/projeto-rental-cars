package services;

import entities.locadora.Locadora;
import entities.usuario.*;
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

        Integer novoIdCliente = obterUltimoIdCliente() + 1;

        switch (opcao) {
            case 1 -> adicionarPessoaFisica(input, email, nome, novoIdCliente, "adicionado");
            case 2 -> adicionarPessoaJuridica(input, email, nome, novoIdCliente, "adicionado");
            case 3 -> adicionarAdministrador(input, email, nome, "adicionado");
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void editar(Scanner input) {
        System.out.println("Digite o email do usuario: ");
        String email  = input.nextLine(); // TODO: validação de email?

        Usuario usuario = usuarioRepository.buscar(email);

        System.out.println("Digite o nome do usuario: ");
        String nome = input.nextLine();

        if(usuario instanceof Administrador administrador) {
            usuarioRepository.remover(usuario);
            adicionarAdministrador(input, email, nome, "editado");
        } else if (usuario instanceof PessoaFisica pessoaFisica) {
            usuarioRepository.remover(usuario);
            adicionarPessoaFisica(input, email, nome, pessoaFisica.getIdCliente(), "editado");
        } else if (usuario instanceof PessoaJuridica pessoaJuridica) {
            usuarioRepository.remover(usuario);
            adicionarPessoaJuridica(input, email, nome, pessoaJuridica.getIdCliente(), "editado");
        } else {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nUsuário não encontrado.\n" + ConsoleColors.RESET);
        }
    }

    public static void buscar(Scanner input) {
        System.out.println("Digite o email do usuario: ");
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
        System.out.println("Digite o email do usuario: ");
        String email  = input.nextLine(); // TODO: validação de email?

        Usuario usuario = usuarioRepository.buscar(email);
        usuario = usuarioRepository.remover(usuario);

        if(usuario != null) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void listar(Scanner input) {
        List<Usuario> usuarios = usuarioRepository.listar();

        int tamanhoPagina = 2;
        int totalPaginas = (int) Math.ceil((double) usuarios.size() / tamanhoPagina);
        int paginaAtual = 1;

        while(true) {
            exibirPagina(usuarios, paginaAtual, tamanhoPagina);
            System.out.println("\nPágina " + paginaAtual + " de " + totalPaginas);
            System.out.println("[N] Próxima página | [P] Página anterior | [S] Sair");

            String opcao = input.nextLine().toUpperCase();
            if (opcao.equals("N") && paginaAtual < totalPaginas) {
                paginaAtual++;
            } else if (opcao.equals("P") && paginaAtual > 1) {
                paginaAtual--;
            } else if (opcao.equals("S")) {
                break;
            } else {
                System.out.println("Comando inválido!");
            }
        }
    }

    private static void exibirPagina(List<Usuario> usuarios, int pagina, int tamanhoPagina) {
        int inicio = (pagina - 1) * tamanhoPagina;
        int fim = Math.min(inicio + tamanhoPagina, usuarios.size());

        for (int i = inicio; i < fim; i++) {
            if(usuarios.get(i) instanceof Administrador administrador) {
                System.out.println(administrador.mostrarAdmin());
            } else if (usuarios.get(i) instanceof PessoaFisica pessoaFisica) {
                System.out.println(pessoaFisica.mostrarPF());
            } else if (usuarios.get(i) instanceof PessoaJuridica pessoaJuridica) {
                System.out.println(pessoaJuridica.mostrarPJ());;
            } else {
                System.out.println("Usuário de tipo desconhecido");
            }
        }
    }

    private static void adicionarAdministrador(Scanner input, String email, String nome, String operacao) {
        System.out.println("Digite o número de registro do funcionário: ");
        Integer numeroRegistro = input.nextInt(); // TODO: verificar/validar o input

        if (!usuarioJaExistente(email)) {
            Administrador adm = new Administrador(nome, email, numeroRegistro);
            usuarioRepository.adicionar(adm);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuario " + operacao + " com sucesso!"+ ConsoleColors.RESET);
        }
    }

    private static void adicionarPessoaJuridica(Scanner input, String email, String nome, Integer idCliente, String operacao) {
        System.out.println("Digite o CNPJ do usuario: ");
        String cnpj = input.nextLine();

        if (!usuarioJaExistente(email)) {
            PessoaJuridica pj = new PessoaJuridica(nome, email, idCliente, cnpj);
            usuarioRepository.adicionar(pj);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuário " + operacao + " com sucesso!" + ConsoleColors.RESET);
        }
    }

    private static void adicionarPessoaFisica(Scanner input, String email, String nome, Integer idCliente, String operacao) {
        System.out.println("Digite o CPF do usuario: ");
        String cpf = input.nextLine();

        if (!usuarioJaExistente(email)) {
            PessoaFisica pf = new PessoaFisica(nome, email, idCliente, cpf);
            usuarioRepository.adicionar(pf);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuario " + operacao + " com sucesso!"+ ConsoleColors.RESET);
        }
    }

    private static boolean usuarioJaExistente(String email) {
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

    private static Integer obterUltimoIdCliente() {
        Integer ultimoID = -1;
        for(Usuario u : Locadora.getUsuarios()) {
            if(u instanceof Cliente) {
                if(((Cliente) u).getIdCliente() > ultimoID) {
                    ultimoID = ((Cliente) u).getIdCliente();
                }
            }
        }
        return ultimoID;
    }
}
