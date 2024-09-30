package entities.veiculo;

public class Caminhao extends Veiculo {

    public Caminhao(Integer id, String placa, String modelo, int ano, String cor, boolean disponivel) {
        super(id, placa, modelo, ano, cor, disponivel);
        super.tipo = TipoVeiculo.CAMINHAO;
    }
}