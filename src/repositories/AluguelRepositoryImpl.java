package repositories;

import entities.locadora.Aluguel;
import entities.locadora.Locadora;
import entities.usuario.Cliente;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AluguelRepositoryImpl implements AluguelRepository {

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
    public List<Aluguel> buscarPorCliente(Cliente cliente) {
        List<Aluguel> resultados = new ArrayList<>();
        for (Aluguel aluguel : Locadora.getAlugueis()) {
            if (aluguel.getCliente().equals(cliente)) {
                resultados.add(aluguel);
            }
        }
        return resultados;
    }
}
