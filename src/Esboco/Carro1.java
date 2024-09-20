package Esboco;

public class Carro1 extends Veiculo {
    public Carro1(String id, String marca, String modelo, int ano, String cor, double valorDiaria) {
        super(id, marca, modelo, ano, cor, valorDiaria);
    }

    @Override
    public String getTipo() {
        return "Carro";
    }
}