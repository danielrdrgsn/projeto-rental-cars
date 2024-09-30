package entities.agencia;

import entities.usuario.Usuario;

import java.util.Objects;

public class Agencia extends Usuario {

    private Integer codigo;
    private String nome;
    private Endereco endereco; // ID
    private String email;

    public Agencia(Integer codigo, String nome, Endereco endereco) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;

    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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
        return Objects.equals(endereco, agencia.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(endereco);
    }

    public String mostrarAgencia() {
        return "Nome da agência: " + nome + "\n"
                + endereco.mostrarEndereco();
    }

    @Override
    public String toString() {
        return codigo + "," +
                nome + "," + endereco.toString();
    }

    public static Agencia fromString(String linha) {
        String[] partes = linha.split(",");
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


    public String getEmail() {
        return this.email;
    }
}
