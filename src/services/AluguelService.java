package services;

import entities.locadora.Aluguel;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;
import repositories.AluguelRepository;

import java.time.LocalDate;

public class AluguelService {

    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }


    public void alugarVeiculo(Cliente cliente, Veiculo veiculo, LocalDate dataAluguel, String localAluguel){

        Aluguel novoAluguel = new Aluguel(cliente, veiculo, dataAluguel, localAluguel);

        novoAluguel.alugarVeiculo();

        aluguelRepository.salvarAluguel(novoAluguel);

        System.out.println(novoAluguel.gerarComprovanteDeAluguel());
    }


    public void devolverVeiculo(Aluguel aluguel, LocalDate dataDevolucao, String localDevolucao){
        aluguel.devolverVeiculo(dataDevolucao, localDevolucao);
        System.out.println(aluguel.gerarComprovanteDeDevolucao());
    }


}
