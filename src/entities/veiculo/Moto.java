package entities.veiculo;

public class Moto extends Veiculo {

    public Moto(String placa, String modelo, int ano, String cor, boolean disponivel, Integer numeroAgencia) {
        super(placa, modelo, ano, disponivel);
        super.tipo = TipoVeiculo.MOTO;
    }
}