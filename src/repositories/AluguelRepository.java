package repositories;

import entities.locadora.Aluguel;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;

import java.util.List;

public interface AluguelRepository {

    void salvarAluguel(Aluguel aluguel);
    List<Aluguel> buscarTodos();
    List<Aluguel> buscarPorCliente(Cliente cliente);
    List<Aluguel> buscarPorVeiculo(Veiculo veiculo);
}