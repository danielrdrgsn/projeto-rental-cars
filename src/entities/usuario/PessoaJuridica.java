package entities.usuario;

import utils.ConsoleColors;

public class PessoaJuridica extends Cliente {

    private String cnpj;

    public PessoaJuridica(Integer idUsuario, String nome, String email, Integer idCliente, String cnpj) {
        super(idUsuario, nome, email, idCliente);
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
                + ConsoleColors.YELLOW_BOLD + "-".repeat(40) + ConsoleColors.RESET;
    }

    @Override
    public String toString() {
        return super.toString() + "," + cnpj;
    }
}
