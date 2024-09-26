package repositories;

import entities.agencia.Agencia;

import java.util.List;

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
        return null;
    }

    @Override
    public List<Agencia> listar() {
        return List.of();
    }
}
