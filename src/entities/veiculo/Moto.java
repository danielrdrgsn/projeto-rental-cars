package entities.veiculo;

public class Moto extends Veiculo {

    public Moto(Integer id, String placa, String modelo, int ano, String cor, boolean disponivel) {
        super(id, placa, modelo, ano, cor, disponivel);
        super.tipo = TipoVeiculo.MOTO;
    }
}