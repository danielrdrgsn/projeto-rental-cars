package entities.usuario;

public abstract class Cliente extends Usuario {

    private Integer idCliente; // ID

    public Cliente(String nome, String email, Integer idCliente) {
        super(nome, email);
        this.idCliente = idCliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return super.toString() + "," + idCliente;
    }
}
