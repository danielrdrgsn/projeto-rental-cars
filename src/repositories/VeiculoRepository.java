package repositories;

import entities.veiculo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VeiculoRepository implements Repositorio<Veiculo, String> {

    private List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    @Override
    public void editar(Veiculo veiculo, String placa) {
        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).getPlaca().equals(placa)) {
                veiculos.set(i, veiculo);
                break;
            }
        }
    }

    @Override
    public Veiculo remover(Veiculo veiculo) {
        if (veiculos.remove(veiculo)) {
            return veiculo;
        }
        return null;
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

    @Override
    public List<Veiculo> listar() {
        return new ArrayList<>(veiculos);
    }
}

