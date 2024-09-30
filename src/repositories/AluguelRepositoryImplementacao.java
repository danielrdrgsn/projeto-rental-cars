package repositories;

import entities.locadora.Aluguel;
import entities.locadora.Locadora;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AluguelRepositoryImplementacao implements AluguelRepository {

    @Override
    public void salvarAluguel(Aluguel aluguel) {
        Locadora.getAlugueis().add(aluguel);
        try {
            LocadoraUtils.salvarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluguel> buscarTodos() {
        return Locadora.getAlugueis();
    }

    @Override
    public List<Aluguel> buscarPorCliente(Cliente cliente) {
        List<Aluguel> resultados = new ArrayList<>();
        for (Aluguel aluguel : Locadora.getAlugueis()) {
            if (aluguel.getCliente().equals(cliente)) {
                resultados.add(aluguel);
            }
        }
        return resultados;
    }

    @Override
    public List<Aluguel> buscarPorVeiculo(Veiculo veiculo) {
        List<Aluguel> resultados = new ArrayList<>();
        for (Aluguel aluguel : Locadora.getAlugueis()) {
            if (aluguel.getVeiculo().equals(veiculo)) {
                resultados.add(aluguel);
            }
        }
        return resultados;
    }
}
