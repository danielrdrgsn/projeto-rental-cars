package repositories;

import entities.locadora.Locadora;
import entities.veiculo.Veiculo;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        try {
            Veiculo antigo = buscar(placa);
            int index = Locadora.getVeiculos().indexOf(antigo);
            if (index != -1) {
                Veiculo editado = Locadora.getVeiculos().get(index);
                editado.setPlaca(placa);
                editado.setCor(veiculo.getCor());
                LocadoraUtils.salvarDadosLocadora();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Veiculo buscar(String placa) {

        Scanner input = new Scanner(System.in);

        System.out.println("Qual veiculo que voce quer buscar? digite a placa");
        String placaVeiculo = input.nextLine();

        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }


    public void listarVeiculos() {
        for (Veiculo v : veiculos) {
            System.out.println(v);
        }
    }

    public List<Veiculo> listar() {
        return List.of();
    }
}
