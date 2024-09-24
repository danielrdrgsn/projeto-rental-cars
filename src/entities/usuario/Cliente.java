package entities.usuario;

public abstract class Cliente extends Usuario {

    private final Integer idCliente; // ID

    public Cliente(String nome, String email, Integer idCliente) {
        super(nome, email);
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String mostrarCliente() {
        return super.mostrarUsuario()
                + "ID Cliente: " + idCliente + "\n";
    }

    @Override
    public String toString() {
        return super.toString() + "," + idCliente;
    }
}
