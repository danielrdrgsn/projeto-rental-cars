package entities.veiculo;

public enum TipoVeiculo {

    CARRO("Carro"),
    MOTO("Moto"),
    CAMINHAO("Caminhão");

    private final String descricao;

    TipoVeiculo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
