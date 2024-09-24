package entities.veiculo;

public class Carro extends Veiculo {

    public Carro(String placa, String modelo, int ano, String cor, boolean disponivel, Integer numeroAgencia) {
        super(placa, modelo, ano, cor, disponivel, numeroAgencia);
        super.tipo = TipoVeiculo.CARRO;
    }

}