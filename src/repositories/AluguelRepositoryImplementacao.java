package repositories;

import entities.locadora.Aluguel;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;

import java.util.List;

public class AluguelRepositoryImplementacao implements AluguelRepository{


    private List<Aluguel> aluguels;

    public AluguelRepositoryImplementacao(List<Aluguel> aluguels) {
        this.aluguels = aluguels;
    }

    @Override
    public void salvarAluguel(Aluguel aluguel) {

    }

    @Override
    public List<Aluguel> buscarTodos() {
        return List.of();
    }

    @Override
    public List<Aluguel> buscarPorCliente(Cliente cliente) {
        return List.of();
    }

    @Override
    public List<Aluguel> buscarPorVeiculo(Veiculo veiculo) {
        return List.of();
    }
}
