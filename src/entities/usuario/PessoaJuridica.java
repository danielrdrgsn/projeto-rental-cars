package entities.usuario;

public class PessoaJuridica extends Cliente {

    private String cnpj;

    public PessoaJuridica(String nome, String email, String cnpj) {
        super(nome, email);
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
        return super.mostrarUsuario() + "CNPJ: " + cnpj + "\n";
    }

    @Override
    public String toString() {
        return super.toString() + "," + cnpj;
    }
}
