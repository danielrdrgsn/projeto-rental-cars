package services;

import entities.locadora.Locadora;
import entities.usuario.*;
import repositories.UsuarioRepository;
import utils.ConsoleColors;

import java.util.Collections;
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

        Integer novoIdUsuario = obterUltimoIdUsuario() + 1;
        Integer novoIdCliente = obterUltimoIdCliente() + 1;

        switch (opcao) {
            case 1 -> adicionarPessoaFisica(input, novoIdUsuario, email, nome, novoIdCliente, "adicionado");
            case 2 -> adicionarPessoaJuridica(input, novoIdUsuario, email, nome, novoIdCliente, "adicionado");
            case 3 -> adicionarAdministrador(input, novoIdUsuario, email, nome, "adicionado");
            default -> System.out.println("Opção inválida.");
        }
    }

    public static void editar(Scanner input) {
        System.out.println("Digite o email do usuario que deseja alterar dados: ");
        String email  = input.nextLine();
        Usuario usuario = usuarioRepository.buscar(email);

        if(usuario == null) {
            System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nUsuário não encontrado.\n" + ConsoleColors.RESET);
            return;
        }

        System.out.println("Digite um novo email para o usuario ou tecle <ENTER> para manter o mesmo: ");
        String novoEmail  = input.nextLine();

        Usuario usuarioExistente = usuarioRepository.buscar(novoEmail);
        while(usuarioExistente != null && !usuarioExistente.getId().equals(usuario.getId())) {
            System.out.println("Email já cadastrado para outro usuário.");
            System.out.println("Digite um novo email para o usuario ou tecle <ENTER> para manter o mesmo: ");
            novoEmail  = input.nextLine();
            usuarioExistente = usuarioRepository.buscar(novoEmail);
        }
        usuario.setEmail(novoEmail.isEmpty()? usuario.getEmail() : novoEmail);

        System.out.println("Digite o novo nome de usuario ou tecle <ENTER> para manter o mesmo: ");
        String novoNome = input.nextLine();
        usuario.setNome(novoNome.isEmpty() ? usuario.getNome() : novoNome);

        if(usuario instanceof Administrador administrador) {
            System.out.println("Digite o novo número de registro do funcionário ou tecle <ENTER> para manter o mesmo: ");
            String entrada = input.nextLine();
            Integer registroAntigo = administrador.getNumeroRegistro();
            Integer novoRegistro = null;
            try {
                if(!entrada.isEmpty()) {
                    novoRegistro = Integer.parseInt(entrada);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException(e.getMessage());
            }
            administrador.setNumeroRegistro(novoRegistro != null ? novoRegistro : registroAntigo);
            usuarioRepository.editar(usuario, email);
        } else if (usuario instanceof PessoaFisica pessoaFisica) {
            System.out.println("Digite o novo CPF ou tecle <ENTER> para manter o mesmo: ");
            String novoCpf = input.nextLine();
            String cpfAntigo = pessoaFisica.getCpf();
            pessoaFisica.setCpf(novoCpf.isEmpty() ? cpfAntigo : novoCpf);
            usuarioRepository.editar(pessoaFisica, email);
        } else if (usuario instanceof PessoaJuridica pessoaJuridica) {
            System.out.println("Digite o novo CNPJ ou tecle <ENTER> para manter o mesmo: ");
            String novoCnpj = input.nextLine();
            String cnpjAntigo = pessoaJuridica.getCnpj();
            pessoaJuridica.setCnpj(novoCnpj.isEmpty() ? cnpjAntigo : novoCnpj);
            usuarioRepository.editar(pessoaJuridica, email);
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

    public static Cliente buscarCliente(String email) {
        Usuario usuario = usuarioRepository.buscar(email);
        if(usuario instanceof Cliente cliente) {
            return cliente;
        }
        return null;
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
        Collections.sort(usuarios);

        int tamanhoPagina = 2; // quantidade de itens por página
        int totalPaginas = (int) Math.ceil((double) usuarios.size() / tamanhoPagina);
        int paginaAtual = 1;

        while(true) {
            exibirPagina(usuarios, paginaAtual, tamanhoPagina);
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

    private static void adicionarAdministrador(Scanner input, Integer idUsuario, String email, String nome, String operacao) {
        System.out.println("Digite o número de registro do funcionário: ");
        Integer numeroRegistro = input.nextInt(); // TODO: verificar/validar o input
        input.nextLine();

        if (!usuarioJaExistente(email)) {
            Administrador adm = new Administrador(idUsuario, nome, email, numeroRegistro);
            usuarioRepository.adicionar(adm);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuario " + operacao + " com sucesso!"+ ConsoleColors.RESET);
        }
    }

    private static void adicionarPessoaJuridica(Scanner input, Integer idUsuario, String email, String nome, Integer idCliente, String operacao) {
        System.out.println("Digite o CNPJ do usuario: ");
        String cnpj = input.nextLine();

        if (!usuarioJaExistente(email)) {
            PessoaJuridica pj = new PessoaJuridica(idUsuario, nome, email, idCliente, cnpj);
            usuarioRepository.adicionar(pj);
            System.out.println(ConsoleColors.GREEN_BOLD + "Usuário " + operacao + " com sucesso!" + ConsoleColors.RESET);
        }
    }

    private static void adicionarPessoaFisica(Scanner input, Integer idUsuario, String email, String nome, Integer idCliente, String operacao) {
        System.out.println("Digite o CPF do usuario: ");
        String cpf = input.nextLine();

        if (!usuarioJaExistente(email)) {
            PessoaFisica pf = new PessoaFisica(idUsuario, nome, email, idCliente, cpf);
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

    private static Integer obterUltimoIdUsuario() {
        Integer ultimoID = -1;
        for(Usuario u : Locadora.getUsuarios()) {
            if(u.getId() > ultimoID) {
                    ultimoID = u.getId();
            }
        }
        return ultimoID;
    }
}
