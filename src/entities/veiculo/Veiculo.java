package entities.veiculo;

import java.util.Objects;

public abstract class Veiculo implements Comparable<Veiculo> {
    private String placa; // ID
    private String modelo;
    private int anoFabricacao;
    private String cor;
    protected TipoVeiculo tipo;
    private boolean disponivel;
    private Integer numeroAgencia;

    public Veiculo(String placa, String modelo, int ano, String cor, boolean disponivel, Integer numeroAgencia) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = ano;
        this.cor = cor;
        this.disponivel = disponivel;
        this.numeroAgencia = numeroAgencia;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void alteraDisponibilidade() {
        this.disponivel = !disponivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(placa);
    }

    @Override
    public int compareTo(Veiculo veiculo) {
        return this.placa.compareTo(veiculo.placa);
    }

    public String mostrarVeiculo() {
        return new StringBuilder()
            .append("Placa: " + placa + "\n")
            .append("Modelo: " + modelo + "\n")
            .append("Ano: " + anoFabricacao + "\n")
            .append("Cor: " + cor + "\n")
            .append("Tipo: " + tipo.getDescricao() + "\n")
            .append("Numero Agencia: " + numeroAgencia + "\n")
            .toString();
    }

    @Override
    public String toString() {
        return    placa + ","
                + modelo + ","
                + anoFabricacao + ","
                + cor + ","
                + tipo + ","
                + disponivel + ","
                + numeroAgencia;
    }

    public static Veiculo fromString(String linha) {
        String[] partes = linha.split(",");
        String placa = partes[0];
        String modelo = partes[1];
        int ano = Integer.parseInt(partes[2]);
        String cor = partes[3];
        TipoVeiculo tipo = TipoVeiculo.valueOf(partes[4]);
        boolean disponivel = Boolean.parseBoolean(partes[5]);
        Integer numeroAgencia = Integer.parseInt(partes[6]);

        return switch(tipo) {
            case CARRO    -> new Carro(placa, modelo, ano, cor, disponivel, numeroAgencia);
            case MOTO     -> new Moto(placa, modelo, ano, cor, disponivel, numeroAgencia);
            case CAMINHAO -> new Caminhao(placa, modelo, ano, cor, disponivel, numeroAgencia);
        };
    };
}