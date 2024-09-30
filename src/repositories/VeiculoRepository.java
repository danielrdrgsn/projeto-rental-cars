package repositories;

import entities.locadora.Locadora;
import entities.veiculo.*;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class VeiculoRepository implements Repositorio<Veiculo, String> {

    @Override
    public void adicionar(Veiculo veiculo) {
        Locadora.getVeiculos().add(veiculo);
        try {
            LocadoraUtils.salvarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editar(Veiculo veiculo, String placa) {

        Scanner input = new Scanner(System.in);

        System.out.println("Qual o placa do veiculo que voce quer atualizar?");
        String placaVeiculo = input.nextLine();

        for (Veiculo v : veiculos) {
            if (placaVeiculo.equals(v.getPlaca())) {
                System.out.println("Qual a placa do veiculo?");
                v.setPlaca(placaVeiculo);
                System.out.println("Qual a modelo do veiculo?");
                v.setModelo(veiculo.getModelo());
                System.out.println("Qual a ano de fabricação do veiculo?");
                v.setAnoFabricacao(veiculo.getAnoFabricacao());
                System.out.println("Qual a cor do veiculo?");
                v.setCor(veiculo.getCor());
                System.out.println("Qual o modelo do veiculo?");
                v.setModelo(veiculo.getModelo());
            }
        }
    }

    public void remover(Veiculo veiculo) {
        Scanner input = new Scanner(System.in);
        System.out.println("Qual o veiculo que voce quer remover? digite a placa");
        String placaVeiculo = input.nextLine();

        if (placaVeiculo.equals(veiculo.getPlaca())) {
            veiculos.remove(veiculo);
        }
    }

    @Override
    public Veiculo buscar(String placa) {
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }

    public Veiculo buscarPorNome(String nome) {
        for (Veiculo v : veiculos) {
            if(v.getModelo().contains(nome)) {
                return v;
            }
        }
        return null;
    }

    public List<Veiculo> listar() {
        return veiculos;
    }
}
