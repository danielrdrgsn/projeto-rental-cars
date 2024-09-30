package repositories;

import entities.agencia.Agencia;
import entities.locadora.Locadora;
import entities.usuario.Administrador;
import entities.usuario.PessoaFisica;
import entities.usuario.PessoaJuridica;
import entities.usuario.Usuario;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.List;

public abstract class UsuarioRepository implements Repositorio<Usuario, String> {
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
    public void editar(Usuario agencia, String email, Object usuario) {
        try {
            LocadoraUtils.carregarDadosLocadora();
            Usuario antigo = buscar(String.valueOf(email));
            int index = Locadora.getUsuarios().indexOf(antigo);
            if(index != -1) {
                Usuario editado = Locadora.getUsuarios().get(index);
                editado.setNome(agencia.getNome());
                editado.setEmail(agencia.getEmail());
                if (editado instanceof Administrador adminUser) {
                    ((Administrador) editado).setNumeroRegistro(adminUser.getNumeroRegistro());
                } else if (editado instanceof PessoaFisica pessoaFisica) {
                    pessoaFisica.setCpf(((PessoaFisica) usuario).getCpf());
                } else if (editado instanceof PessoaJuridica pessoaJuridica) {
                    pessoaJuridica.setCnpj(((PessoaJuridica) usuario).getCnpj());
                }
                LocadoraUtils.salvarDadosLocadora();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    public Agencia remover(Integer id) {
        return null;
    }

    @Override
    public Usuario buscar(String email) {
        carregaDadosLocadora();
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
        carregaDadosLocadora();
        return Locadora.getUsuarios();
    }

    private static void carregaDadosLocadora() {
        try {
            LocadoraUtils.carregarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
