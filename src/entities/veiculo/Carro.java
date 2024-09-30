package entities.veiculo;

public class Carro extends Veiculo {

    public Carro(String placa, String modelo, int ano, String cor, boolean disponivel, Integer numeroAgencia) {
        super(placa, modelo, ano, disponivel);
        super.tipo = TipoVeiculo.CARRO;
    }

}