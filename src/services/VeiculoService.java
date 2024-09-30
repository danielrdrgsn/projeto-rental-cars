package services;

import entities.veiculo.Veiculo;
import repositories.VeiculoRepository;

import java.util.Scanner;

public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public void adicionar(Veiculo veiculo) {
        veiculoRepository.adicionar(veiculo);
        System.out.println("Veículo adicionado!");
    }

    public void editar(String placa, Veiculo veiculoAtualizado) {
        Veiculo veiculoExistente = veiculoRepository.buscar(placa);
        if (veiculoExistente != null) {
            veiculoRepository.editar(veiculoAtualizado, placa);
            System.out.println("Veículo atualizado!");
        } else {
            System.out.println("Veículo não encontrado para atualização.");
        }
    }

    public Veiculo buscar(String placa) {
        Veiculo veiculo = veiculoRepository.buscar(placa);
        if (veiculo != null) {
            System.out.println("Veículo encontrado: " + veiculo);
        } else {
            System.out.println("Veículo não encontrado.");
        }
        return veiculo;
    }

    public void remover(String placa) {
        Veiculo veiculo = veiculoRepository.buscar(placa);
        if (veiculo != null) {
            veiculoRepository.remover(veiculo);
            System.out.println("Veículo removido com sucesso!");
        } else {
            System.out.println("Veículo não encontrado para remoção.");
        }
    }

    public void listar() {
        veiculoRepository.listar().forEach(System.out::println);
    }


}
