package repositories;

import entities.veiculo.Veiculo;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepositoryImpl extends VeiculoRepository {

    private final List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
        System.out.println("Veículo adicionado: " + veiculo);
    }

    @Override
    public void editar(Veiculo veiculo, String placa, Object usuario) {

    }

    @Override
    public void editar(Veiculo veiculo) {
        Veiculo veiculoExistente = buscar(veiculo.getPlaca());
        if (veiculoExistente != null) {
            veiculoExistente.setModelo(veiculo.getModelo());
            veiculoExistente.setAnoFabricacao(veiculo.getAnoFabricacao());
            veiculoExistente.setCor(veiculo.getCor());
            veiculoExistente.setNumeroAgencia(veiculo.getNumeroAgencia());
            System.out.println("Veículo atualizado com sucesso.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    @Override
    public Veiculo remover(String placa) {
        Veiculo veiculo = buscar(placa);
        if (veiculo != null) {
            veiculos.remove(veiculo);
        }
        return veiculo;
    }

    @Override
    public Veiculo buscar(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    @Override
    public List<Veiculo> listar() {
        return veiculos;
    }
}
