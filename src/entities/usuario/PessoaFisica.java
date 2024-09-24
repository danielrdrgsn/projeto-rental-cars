package entities.usuario;

public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(String nome, String email, Integer idCliente, String cpf) {
        super(nome, email, idCliente);
        super.tipoUsuario = TipoUsuario.PF;
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String mostrarPF() {
        return super.mostrarCliente() + "CPF: " + cpf + "\n"
                + "-".repeat(40);
    }

    @Override
    public String toString() {
        return super.toString() + "," + cpf;
    }
}
