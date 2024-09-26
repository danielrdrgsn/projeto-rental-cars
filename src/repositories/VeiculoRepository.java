package repositories;

import entities.veiculo.Veiculo;

import java.util.List;

public class VeiculoRepository implements Repositorio<Veiculo, String> {


    @Override
    public void adicionar(Veiculo veiculo) {

    }

    @Override
    public void editar(Veiculo veiculo, String placa) {

    }

    @Override
    public Veiculo remover(Veiculo veiculo) {
        return null;
    }

    @Override
    public Veiculo buscar(String placa) {
        return null;
    }

    @Override
    public List<Veiculo> listar() {
        return List.of();
    }
}
