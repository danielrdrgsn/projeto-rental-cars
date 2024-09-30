package entities.agencia;

import java.util.Objects;

public class Agencia {

    private final Integer codigo;
    private String nome;
    private Endereco endereco; // ID

    public Agencia(Integer codigo, String nome, Endereco endereco) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agencia agencia = (Agencia) o;
        return Objects.equals(codigo, agencia.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    public String mostrarAgencia() {
        return "Nome da agência: " + nome + "\n"
                + endereco.mostrarEndereco();
    }

    @Override
    public String toString() {
        return codigo + ";" +
                nome + ";" + endereco.toString();
    }

    public static Agencia fromString(String linha) {
        String[] partes = linha.split(";");
        Integer codigo = Integer.parseInt(partes[0]);
        String nome = partes[1];
        String logradouro = partes[2];
        String numero = partes[3];
        String complemento = partes[4];
        String cidade = partes[5];
        String estado = partes[6];
        String cep = partes[7];
        Endereco endereco = new Endereco(logradouro, numero, complemento, cidade, estado, cep);

        return new Agencia(codigo, nome, endereco);
    }
}
