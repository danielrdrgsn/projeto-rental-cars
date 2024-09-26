package entities.usuario;

import utils.ConsoleColors;

public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(Integer idUsuario, String nome, String email, Integer idCliente, String cpf) {
        super(idUsuario, nome, email, idCliente);
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
                + ConsoleColors.YELLOW_BOLD + "-".repeat(40) + ConsoleColors.RESET;
    }

    @Override
    public String toString() {
        return super.toString() + "," + cpf;
    }
}
