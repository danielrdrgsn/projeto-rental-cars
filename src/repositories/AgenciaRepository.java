package repositories;

import entities.agencia.Agencia;
import entities.locadora.Locadora;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
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
        try {
            LocadoraUtils.carregarDadosLocadora();
            List<Agencia> agencias = Locadora.getAgencias();
            for (Agencia agencia : agencias) {
                if (agencia.getCodigo() == codigo) {
                    return agencia;
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Agencia> listar() {
        return List.of();
    }
}
