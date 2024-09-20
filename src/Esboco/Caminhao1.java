package Esboco;

public class Caminhao1 extends Veiculo {
    public Caminhao1(String id, String marca, String modelo, int ano, String cor, double valorDiaria) {
        super(id, marca, modelo, ano, cor, valorDiaria);
    }

    @Override
    public String getTipo() {
        return "Caminhão";
    }
}