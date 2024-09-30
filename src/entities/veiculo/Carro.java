package entities.veiculo;

public class Carro extends Veiculo {

    public Carro(Integer id, String placa, String modelo, int ano, String cor, boolean disponivel) {
        super(id, placa, modelo, ano, cor, disponivel);
        super.tipo = TipoVeiculo.CARRO;
    }

}