package repositories;

import entities.agencia.Agencia;
import java.util.ArrayList;
import java.util.List;

public abstract class AgenciaRepository implements Repositorio<Agencia, Integer> {
    private final List<Agencia> agencias = new ArrayList<>();

    @Override
    public void adicionar(Agencia agencia) {
        agencias.add(agencia);
    }

    @Override
    public Agencia remover(Agencia agencia) {
        return null;
    }

    @Override
    public Agencia remover(Integer id) {
        Agencia agencia = buscar(id);
        if (agencia != null) {
            agencias.remove(agencia);
        }
        return agencia;
    }

    @Override
    public Agencia buscar(Integer id) {
        for (Agencia agencia : agencias) {
            if (agencia.getCodigo().equals(id)) {
                return agencia;
            }
        }
        return null;
    }

    @Override
    public List<Agencia> listar() {
        return agencias;
    }
}
