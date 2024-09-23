package entities.usuario;

import entities.locadora.Locadora;

public abstract class Cliente extends Usuario {

    private Integer idCliente; // ID

    public Cliente(String nome, String email) {
        super(nome, email);
        this.idCliente = Locadora.getUsuarios().size() + 1;
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
