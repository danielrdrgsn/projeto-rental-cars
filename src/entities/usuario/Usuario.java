package entities.usuario;

import utils.ConsoleColors;

import java.util.Objects;

public abstract class Usuario implements Comparable<Usuario> {

    private final Integer id;
    private String nome;
    private String email; // ID
    protected TipoUsuario tipoUsuario;

    public Usuario(Integer Id, String nome, String email) {
        this.id = Id;
        this.nome = nome;
        this.email = email;
    }

    public Integer getId() {
        return id;
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
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public int compareTo(Usuario usuario) {
        return this.nome.compareTo(usuario.nome);
    }

    public String mostrarUsuario() {
        return ConsoleColors.BLUE_BOLD + "_".repeat(40) + ConsoleColors.RESET + "\n" +
                "Nome: " + nome + "\n" +
                "Email: " + email + "\n" +
                "Tipo: " + tipoUsuario.getDescricao() + "\n";
    }

    @Override
    public String toString() {
        return id + ","
                + nome + ","
                + email + ","
                + tipoUsuario;
    }

    public static Usuario fromString(String linha) {
        String[] partes = linha.split(",");
        Integer id = Integer.parseInt(partes[0]);
        String nome = partes[1];
        String email = partes[2];
        TipoUsuario tipoUsuario = TipoUsuario.valueOf(partes[3]);

        return switch (tipoUsuario) {
            case ADMIN -> new Administrador(id, nome, email, Integer.parseInt(partes[4]));
            case PF -> new PessoaFisica(id, nome, email, Integer.parseInt(partes[4]), partes[5]);
            case PJ -> new PessoaJuridica(id, nome, email, Integer.parseInt(partes[4]), partes[5]);
        };
    }
}