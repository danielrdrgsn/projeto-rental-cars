package entities.veiculo;

public class Moto extends Veiculo {

    public Moto(Integer id, String placa, String modelo, int ano, String cor, boolean disponivel, Integer codAgenciaAtual) {
        super(id, placa, modelo, ano, cor, disponivel, codAgenciaAtual);
        super.tipo = TipoVeiculo.MOTO;
    }
}