package repositories;

import entities.agencia.Agencia;
import entities.locadora.Locadora;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AgenciaRepository implements Repositorio<Agencia, Integer> {


    @Override
    public void adicionar(Agencia agencia) {

    }

    @Override
    public void editar(Agencia agencia, Integer id) {

    }

    @Override
    public Agencia remover(Agencia agencia) {
        return null;
    }

    @Override
    public Agencia buscar(Integer codigo) {
        List<Agencia> agencias = Locadora.getAgencias();
        for (Agencia agencia : agencias) {
            if (Objects.equals(agencia.getCodigo(), codigo)) {
                return agencia;
            }
        }
        return null;
    }

    @Override
    public List<Agencia> listar() {
        return List.of();
    }
}
