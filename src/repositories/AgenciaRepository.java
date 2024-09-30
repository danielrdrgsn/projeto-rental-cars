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
        Locadora.getAgencias().add(agencia);
    }

    @Override
    public void editar(Agencia agencia, Integer codAgencia) {
        try {
            Agencia antiga = buscar(codAgencia);
            int index = Locadora.getAgencias().indexOf(antiga);
            if (index != -1) {
                Agencia editado = Locadora.getAgencias().get(index);
                editado.setNome(agencia.getNome());
                editado.setEndereco(agencia.getEndereco());
                LocadoraUtils.salvarDadosLocadora();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Agencia remover(Agencia agencia) {
        try {
            int index = Locadora.getAgencias().indexOf(buscar(agencia.getCodigo()));
            if (index != -1) {
                Agencia removido = Locadora.getAgencias().remove(index);
                LocadoraUtils.salvarDadosLocadora();
                return removido;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Agencia buscar(Integer id) {
        for(Agencia agencia : Locadora.getAgencias()) {
            if(Objects.equals(agencia.getCodigo(), id)) {
                return agencia;
            }
        }
        return null;
    }

    @Override
    public List<Agencia> listar() {
        return Locadora.getAgencias();
    }
}
