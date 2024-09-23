package entities.usuario;

public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(String nome, String email, String cpf) {
        super(nome, email);
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
        return super.mostrarUsuario() + "CPF: " + cpf + "\n";
    }

    @Override
    public String toString() {
        return super.toString() + "," + cpf;
    }
}
