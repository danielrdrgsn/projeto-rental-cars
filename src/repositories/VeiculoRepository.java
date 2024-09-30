package repositories;

import entities.agencia.Agencia;
import entities.usuario.Usuario;
import entities.veiculo.Veiculo;

import java.util.List;

public abstract class VeiculoRepository implements Repositorio<Veiculo, String> {


    @Override
    public void adicionar(Veiculo veiculo) {

    }

    @Override
    public void editar(Usuario veiculo, String placa, Object usuario) {

    }

    @Override
    public Veiculo remover(Veiculo veiculo) {
        return null;
    }

    @Override
    public void editar(Agencia agencia, String email) {

    }

    @Override
    public Agencia remover(Integer id) {
        return null;
    }

    public abstract void editar(Veiculo veiculo, String placa, Object usuario);

    public abstract void editar(Veiculo veiculo);

    public abstract Veiculo remover(String placa);

    @Override
    public Veiculo buscar(String placa) {
        return null;
    }

    @Override
    public List<Veiculo> listar() {
        return List.of();
    }
}
