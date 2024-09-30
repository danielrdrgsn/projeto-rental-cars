package repositories;

import entities.agencia.Agencia;

import java.util.List;

import entities.agencia.Agencia;

public abstract class AgenciaRepository implements Repositorio<Agencia, Integer> {
    private List<Agencia> agencias = new ArrayList<>();


    @Override
    public void adicionar(Agencia agencia) {
        agencias.add(agencia);
    }

    @Override
    public void editar(Agencia agencia, String email) {
        Agencia agenciaExistente = buscar(String.valueOf(email));
        if (agenciaExistente != null) {
            agenciaExistente.setNome(agencia.getNome());
            agenciaExistente.setEndereco(agencia.getEndereco());
        }
    }

    }

    @Override
    public Agencia remover(Integer id) {
        Agencia agencia = buscar(id);
        if (agencia != null) {
            listar().remove(agencia);
        }
        return agencia;
    }

    @Override
    public Agencia buscar(String email) {
        return null;
    }

    public abstract Agencia buscar(Integer id);

    @Override
    public List<Agencia> listar() {
        return agencias;
    }
}
