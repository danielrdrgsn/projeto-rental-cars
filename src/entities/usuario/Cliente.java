package entities.usuario;

public abstract class Cliente extends Usuario {

    private final Integer idCliente; // ID

    public Cliente(Integer idUsuario, String nome, String email, Integer idCliente) {
        super(idUsuario, nome, email);
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String mostrarCliente() {
        return super.mostrarUsuario();
    }

    @Override
    public String toString() {
        return super.toString() + "," + idCliente;
    }
}
