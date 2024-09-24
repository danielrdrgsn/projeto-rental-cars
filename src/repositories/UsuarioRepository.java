package repositories;

import entities.locadora.Locadora;
import entities.usuario.Usuario;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.List;

public class UsuarioRepository implements Repositorio<Usuario, String> {
    @Override
    public void adicionar(Usuario usuario) {
        Locadora.getUsuarios().add(usuario);
        try {
            LocadoraUtils.salvarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editar(Usuario usuario) {
        for(Usuario u : Locadora.getUsuarios()){
            if(u.getEmail().equals(usuario.getEmail())){
                u.setNome(usuario.getNome());
                // TODO: verificar o tipo de usuário pra atualizar as infos corretas
            }
        }

    }

    @Override
    public Usuario remover(Usuario usuario) {
        try {
            LocadoraUtils.carregarDadosLocadora();
            int index = Locadora.getUsuarios().indexOf(usuario);
            if(index != -1) {
                Usuario removido = Locadora.getUsuarios().remove(index);
                LocadoraUtils.salvarDadosLocadora();
                return removido;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Usuario buscar(String email) {
        try {
            LocadoraUtils.carregarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Usuario> usuarios = Locadora.getUsuarios();
        for(Usuario u : usuarios){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<Usuario> listar() {
        try {
            LocadoraUtils.carregarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Locadora.getUsuarios();
    }
}
