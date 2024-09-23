package repositories;

import entities.locadora.Locadora;
import entities.veiculo.Veiculo;

import java.util.List;

public class VeiculoRepository implements Repositorio<Veiculo, String> {

    public VeiculoRepository() {}

    @Override
    public void adicionar(Veiculo veiculo) {
        Locadora.getVeiculos().add(veiculo);
    }

    @Override
    public void editar(Veiculo veiculo) {
        for(Veiculo v : Locadora.getVeiculos()) {
            if(v.getPlaca().equals(veiculo.getPlaca())) {
                v.setPlaca(veiculo.getPlaca());
                v.setModelo(veiculo.getModelo());
                v.setCor(veiculo.getCor());
            }
        }
    }

    @Override
    public void remover(Veiculo veiculo) {
        // TODO
    }

    @Override
    public Veiculo buscar(String placa) {
        for(Veiculo v : Locadora.getVeiculos()) {
            if(v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public List<Veiculo> listar() {
        return Locadora.getVeiculos();
    }
}
