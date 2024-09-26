package entities.usuario;

import utils.ConsoleColors;

public class Administrador extends Usuario {

    private Integer numeroRegistro;

    public Administrador(Integer idUsuario, String nome, String email, Integer numeroRegistro) {
        super(idUsuario, nome, email);
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
                + ConsoleColors.YELLOW_BOLD + "-".repeat(40) + ConsoleColors.RESET;
    }

    @Override
    public String toString() {
        return super.toString() + "," + numeroRegistro;
    }
}
