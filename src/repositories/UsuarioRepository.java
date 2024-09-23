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
    public void remover(Usuario usuario) {
        Locadora.getUsuarios().remove(usuario);
    }

    @Override
    public Usuario buscar(String id) {
        // TODO
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return Locadora.getUsuarios();
    }
}
