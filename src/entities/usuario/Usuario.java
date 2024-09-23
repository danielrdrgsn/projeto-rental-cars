package entities.usuario;

import java.util.Objects;

public abstract class Usuario implements Comparable<Usuario> {

    private String nome;
    private String email; // ID
    protected TipoUsuario tipoUsuario;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public int compareTo(Usuario usuario) {
        return this.nome.compareTo(usuario.nome);
    }

    public String mostrarUsuario() {
        return new StringBuilder()
                .append("_".repeat(50)+ "\n")
                .append("Nome: " + nome + "\n")
                .append("Email: " + email + "\n")
                .append("Tipo: " + tipoUsuario.getDescricao() + "\n")
                .toString();
    }

    @Override
    public String toString() {
        return    nome + ","
                + email + ","
                + tipoUsuario;
    }

    public static Usuario fromString(String linha) {
        String[] partes = linha.split(",");
        String nome = partes[0];
        String email = partes[1];
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(partes[2]);

        return switch (tipoUsuario) {
            case ADMIN -> new Administrador(nome, email,Integer.parseInt(partes[3]));
            case PF -> new PessoaFisica(nome, email, partes[4]);
            case PJ -> new PessoaJuridica(nome, email, partes[4]);
        };
    }

    //    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        boolean executando = true;
//
//        while (executando) {
//            System.out.println("\nMENU - ADMINISTRADOR");
//            System.out.println("\n1. Agências"); // ELOISE
//            System.out.println("2. Veículos"); // THIAGO
//            System.out.println("3. Aluguéis"); // VINICIUS
//            System.out.println("4. Clientes"); // SAMUEL
//            System.out.println("5. Sair");
//            System.out.print("Escolha uma opção: ");
//
//            int opcao = sc.nextInt();
//            sc.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    cadastrarAgencia(sc);
//                    break;
//                case 2:
//                    cadastrarCliente(sc);
//                    break;
//                case 3:
//                    cadastrarVeiculo(sc);
//                    break;
//                case 4:
//                    alugarVeiculo(sc);
//                    break;
//                case 5:
//                    devolverVeiculo(sc);
//                    break;
//                case 6:
//                    listarVeiculosDisponiveis();
//                    break;
//                case 7:
//                    listarClientes();
//                    break;
//                case 8:
//                    executando = false;
//                    break;
//                default:
//                    System.out.println("Opção inválida.");
//            }
//        }
//
//        sc.close();
//        System.out.println("Saindo...");
//    }
}