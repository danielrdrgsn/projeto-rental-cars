package entities.veiculo;

public class Carro extends Veiculo {

    public Carro(Integer id, String placa, String modelo, int ano, String cor, boolean disponivel, Integer codAgenciaAtual) {
        super(id, placa, modelo, ano, cor, disponivel, codAgenciaAtual);
        super.tipo = TipoVeiculo.CARRO;
    }

}