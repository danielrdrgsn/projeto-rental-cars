package repositories;

import entities.agencia.Agencia;
import entities.locadora.Locadora;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AgenciaRepository implements Repositorio<Agencia, Integer> {

    @Override
    public void adicionar(Agencia agencia) {
        Locadora.getAgencias().add(agencia);
        try {
            LocadoraUtils.salvarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void editar(Agencia agencia, Integer codAgencia) {
        try {
            Agencia antiga = buscar(codAgencia);
            if (antiga != null) {
                antiga.setNome(agencia.getNome());
                antiga.setEndereco(agencia.getEndereco());
                LocadoraUtils.salvarDadosLocadora();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os dados da locadora.", e);
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
        for (Agencia agencia : Locadora.getAgencias()) {
            if (Objects.equals(agencia.getCodigo(), id)) {
                return agencia;
            }
        }
        return null;
    }

    @Override
    public List<Agencia> listar() {
        List<Agencia> agencias = Locadora.getAgencias();
        Collections.sort(agencias);
        return agencias;
    }

    public static List<Agencia> buscarPorParteDoNome(String nome) {
        List<Agencia> agencias = Locadora.getAgencias();
        List<Agencia> resultado = new ArrayList<>();
        for (Agencia agencia : agencias) {
            if (agencia.getNome().toLowerCase().contains(nome.toLowerCase()) || agencia.getEndereco().getLogradouro().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(agencia);
            }
        }
        return resultado;
    }

    public boolean existeAgenciaComMesmosDados(Agencia agencia) {
        for (Agencia ag : Locadora.getAgencias()) {
            if (ag.equals(agencia)) {
                return true;
            }
        }
        return false;
    }
}
