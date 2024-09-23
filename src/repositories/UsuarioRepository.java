package repositories;

import entities.locadora.Locadora;
import entities.usuario.Usuario;

import java.util.List;

public class UsuarioRepository implements Repositorio<Usuario, String> {
    @Override
    public void adicionar(Usuario usuario) {
        // TODO: verificar se já existe um email igual cadastrado, pra evitar duplicatas
        Locadora.getUsuarios().add(usuario);
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
