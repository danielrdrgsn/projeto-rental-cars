package repositories;

import entities.agencia.Agencia;
import entities.locadora.Locadora;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AgenciaRepository implements Repositorio<Agencia, Integer> {

    @Override
    public void adicionar(Agencia agencia) {
        Locadora.getAgencias().add(agencia);
        salvarDados();
    }

    @Override
    public void editar(Agencia agencia, Integer codAgencia) {
        buscar(codAgencia).ifPresentOrElse(antiga -> {
            antiga.setNome(agencia.getNome());
            antiga.setEndereco(agencia.getEndereco());
            salvarDados();
        }, () -> {
            throw new NoSuchElementException("Agência com código " + codAgencia + " não encontrada.");
        });
    }

    @Override
    public Agencia remover(Agencia agencia) {
        Optional<Agencia> encontrada = buscar(agencia.getCodigo());
        if (encontrada.isPresent()) {
            Locadora.getAgencias().remove(encontrada.get());
            salvarDados();
            return encontrada.get();
        }
        return null;
    }

    @Override
    public Optional<Agencia> buscar(Integer id) {
        return Locadora.getAgencias().stream()
                .filter(agencia -> Objects.equals(agencia.getCodigo(), id))
                .findFirst();
    }

    @Override
    public List<Agencia> listar() {
        return Locadora.getAgencias().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public static List<Agencia> buscarPorParteDoNome(String nome) {
        String nomeLower = nome.toLowerCase();
        return Locadora.getAgencias().stream()
                .filter(agencia -> agencia.getNome().toLowerCase().contains(nomeLower) ||
                        agencia.getEndereco().getLogradouro().toLowerCase().contains(nomeLower))
                .collect(Collectors.toList());
    }

    public boolean existeAgenciaComMesmosDados(Agencia agencia) {
        return Locadora.getAgencias().stream().anyMatch(ag -> ag.equals(agencia));
    }

    private void salvarDados() {
        try {
            LocadoraUtils.salvarDadosLocadora();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar os dados da locadora.", e);
        }
    }
}
