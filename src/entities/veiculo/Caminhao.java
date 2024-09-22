package entities.veiculo;

public class Caminhao extends Veiculo {

    public Caminhao(String placa, String modelo, int ano, String cor, boolean disponivel, Integer numeroAgencia) {
        super(placa, modelo, ano, cor, disponivel, numeroAgencia);
        super.tipo = TipoVeiculo.CAMINHAO;
    }
}