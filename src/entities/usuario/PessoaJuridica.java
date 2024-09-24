package entities.usuario;

public class PessoaJuridica extends Cliente {

    private String cnpj;

    public PessoaJuridica(String nome, String email, Integer idCliente, String cnpj) {
        super(nome, email, idCliente);
        super.tipoUsuario = TipoUsuario.PJ;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String mostrarPJ() {
        return super.mostrarCliente() + "CNPJ: " + cnpj + "\n"
                + "-".repeat(40);
    }

    @Override
    public String toString() {
        return super.toString() + "," + cnpj;
    }
}
