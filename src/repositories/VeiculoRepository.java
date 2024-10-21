package repositories;

import entities.locadora.Locadora;
import entities.veiculo.Veiculo;
import utils.persistencia.LocadoraUtils;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class VeiculoRepository implements Repositorio<Veiculo, String> {

    @Override
    public void adicionar(Veiculo veiculo) {
        Locadora.getVeiculos().add(veiculo);
        salvarDados();
    }

    @Override
    public void editar(Veiculo veiculo, String placaAntiga) {
        buscar(placaAntiga).ifPresentOrElse(antigo -> {
            antigo.setPlaca(veiculo.getPlaca());
            antigo.setCor(veiculo.getCor());
            salvarDados();
        }, () -> {
            throw new NoSuchElementException("Veículo com placa " + placaAntiga + " não encontrado.");
        });
    }

    @Override
    public Optional<Veiculo> buscar(String placa) {
        return Locadora.getVeiculos().stream()
                .filter(v -> v.getPlaca().equals(placa))
                .findFirst();
    }

    @Override
    public Veiculo remover(Veiculo veiculo) {
        if (Locadora.getVeiculos().remove(veiculo)) {
            salvarDados();
            return veiculo;
        }
        return null;
    }

    public List<Veiculo> buscarPorModelo(String modelo) {
        return Locadora.getVeiculos().stream()
                .filter(v -> v.getModelo().contains(modelo))
                .collect(Collectors.toList());
    }

    @Override
    public List<Veiculo> listar() {
        return Locadora.getVeiculos().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public Optional<Veiculo> buscarPorId(Integer codigo) {
        return Locadora.getVeiculos().stream()
                .filter(v -> Objects.equals(v.getId(), codigo))
                .findFirst();
    }

    public List<Veiculo> buscarVeiculosDisponiveis() {
        return Locadora.getVeiculos().stream()
                .filter(Veiculo::isDisponivel)
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