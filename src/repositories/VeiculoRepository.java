package repositories;

import entities.locadora.Locadora;
import entities.veiculo.Veiculo;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class VeiculoRepository implements Repositorio<Veiculo, String> {

    @Override
    public void adicionar(Veiculo veiculo) {
        Locadora.getVeiculos().add(veiculo);
        try {
            LocadoraUtils.salvarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editar(Veiculo veiculo, String placaAntiga) {
        try {
            Veiculo antigo = buscar(placaAntiga);
            if (antigo != null) {
                antigo.setPlaca(veiculo.getPlaca());
                antigo.setCor(veiculo.getCor());
                LocadoraUtils.salvarDadosLocadora();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar dados da locadora: " + e.getMessage(), e);
        }
    }

    @Override
    public Veiculo buscar(String placa) {
        List<Veiculo> veiculos = Locadora.getVeiculos();
        for (Veiculo v : veiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }

    public Veiculo remover(Veiculo veiculo) {
        try {
            int index = Locadora.getVeiculos().indexOf(veiculo);
            if (index != -1) {
                Veiculo removido = Locadora.getVeiculos().remove(index);
                LocadoraUtils.salvarDadosLocadora();
                return removido;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Veiculo> buscarPorModelo(String modelo) {
        List<Veiculo> veiculos = Locadora.getVeiculos();
        List<Veiculo> resultado = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if(v.getModelo().contains(modelo)) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    public List<Veiculo> listar() {
        List<Veiculo> veiculos = Locadora.getVeiculos();
        Collections.sort(veiculos);
        return veiculos;
    }

    public Veiculo buscarPorId(Integer codigo) {
        List<Veiculo> veiculos = Locadora.getVeiculos();
        for (Veiculo v : veiculos) {
            if (Objects.equals(v.getId(), codigo)) {
                return v;
            }
        }
        return null;
    }

    public List<Veiculo> bucarVeiculosDisponiveis() {
        List<Veiculo> resultado = new ArrayList<>();
        for(Veiculo v:Locadora.getVeiculos()) {
            if(v.isDisponivel()) {
                resultado.add(v);
            }
        }
        Collections.sort(resultado);
        return resultado;
    }
}