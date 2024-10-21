package repositories;

import entities.locadora.Locadora;
import entities.usuario.Administrador;
import entities.usuario.PessoaFisica;
import entities.usuario.PessoaJuridica;
import entities.usuario.Usuario;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioRepository implements Repositorio<Usuario, String> {
    @Override
    public void adicionar(Usuario usuario) {
        Locadora.getUsuarios().add(usuario);
        salvarDados();
    }

    @Override
    public void editar(Usuario usuario, String email) {
        buscar(email).ifPresentOrElse(antigo -> {
            antigo.setNome(usuario.getNome());
            antigo.setEmail(usuario.getEmail());

            if (antigo instanceof Administrador adminUser) {
                adminUser.setNumeroRegistro(((Administrador) usuario).getNumeroRegistro());
            } else if (antigo instanceof PessoaFisica pessoaFisica) {
                pessoaFisica.setCpf(((PessoaFisica) usuario).getCpf());
            } else if (antigo instanceof PessoaJuridica pessoaJuridica) {
                pessoaJuridica.setCnpj(((PessoaJuridica) usuario).getCnpj());
            }
            salvarDados();
        }, () -> {
            throw new NoSuchElementException("Usuário com email " + email + " não encontrado.");
        });
    }

    @Override
    public Optional<Usuario> buscar(String email) {
        return Locadora.getUsuarios().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Usuario remover(Usuario usuario) {
        if (Locadora.getUsuarios().remove(usuario)) {
            salvarDados();
            return usuario;
        }
        return null;
    }

    public static List<Usuario> buscarPorParteDoNome(String parteDoNome) {
        String nomeLower = parteDoNome.toLowerCase();
        return Locadora.getUsuarios().stream()
                .filter(usuario -> usuario.getNome().toLowerCase().contains(nomeLower))
                .collect(Collectors.toList());
    }

    @Override
    public List<Usuario> listar() {
        return Locadora.getUsuarios().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    private void salvarDados() {
        try {
            LocadoraUtils.salvarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar dados da locadora.", e);
        }
    }
}
