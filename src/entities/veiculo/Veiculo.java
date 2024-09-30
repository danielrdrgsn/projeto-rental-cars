package entities.veiculo;

import java.util.Objects;

public abstract class Veiculo implements Comparable<Veiculo> {
    private Integer id;
    private String placa; // ID
    private String modelo;
    private int anoFabricacao;
    private String cor;
    protected TipoVeiculo tipo;
    private boolean disponivel;

    public Veiculo(Integer id, String placa, String modelo, int ano, String cor, boolean disponivel) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = ano;
        this.cor = cor;
        this.disponivel = disponivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return Objects.equals(id, veiculo.id) && Objects.equals(placa, veiculo.placa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placa);
    }

    @Override
    public int compareTo(Veiculo veiculo) {
        return this.placa.compareTo(veiculo.placa);
    }

    public String mostrarVeiculo() {
        return  "ID: " + id + "\n" +
                "Placa: " + placa + "\n" +
                "Modelo: " + modelo + "\n" +
                "Ano: " + anoFabricacao + "\n" +
                "Cor: " + cor + "\n" +
                "Tipo: " + tipo.getDescricao() + "\n";
    }

    @Override
    public String toString() {
        return    id + ","
                + placa + ","
                + modelo + ","
                + anoFabricacao + ","
                + cor + ","
                + tipo + ","
                + disponivel;
    }

    public static Veiculo fromString(String linha) {
        String[] partes = linha.split(",");
        Integer id = Integer.parseInt(partes[0]);
        String placa = partes[1];
        String modelo = partes[2];
        int ano = Integer.parseInt(partes[3]);
        String cor = partes[4];
        TipoVeiculo tipo = TipoVeiculo.valueOf(partes[5]);
        boolean disponivel = Boolean.parseBoolean(partes[6]);

        return switch(tipo) {
            case CARRO    -> new Carro(id, placa, modelo, ano, cor, disponivel);
            case MOTO     -> new Moto(id, placa, modelo, ano, cor, disponivel);
            case CAMINHAO -> new Caminhao(id, placa, modelo, ano, cor, disponivel);
        };
    };
}