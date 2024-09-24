package entities.usuario;

public class Administrador extends Usuario {

    private Integer numeroRegistro;

    public Administrador(String nome, String email, Integer numeroRegistro) {
        super(nome, email);
        super.tipoUsuario = TipoUsuario.ADMIN;
        this.numeroRegistro = numeroRegistro;
    }

    public Integer getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(Integer numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String mostrarAdmin() {

        return super.mostrarUsuario()
                + "Registro nº: " + numeroRegistro + "\n"
                + "-".repeat(40);
    }

    @Override
    public String toString() {
        return super.toString() + "," + numeroRegistro;
    }
}
