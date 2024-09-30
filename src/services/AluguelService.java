package services;

import entities.locadora.Aluguel;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;
import repositories.AluguelRepository;

import java.time.LocalDate;
import java.util.List;

public class AluguelService {

    private final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public void alugarVeiculo(Cliente cliente, Veiculo veiculo, LocalDate dataAluguel, String localAluguel) {

        Aluguel novoAluguel = new Aluguel(cliente, veiculo, dataAluguel, localAluguel);

        novoAluguel.alugarVeiculo();

        aluguelRepository.salvarAluguel(novoAluguel);

        System.out.println(novoAluguel.gerarComprovanteDeAluguel());
    }

    public void devolverVeiculo(Aluguel aluguel, LocalDate dataDevolucao, String localDevolucao) {
        aluguel.devolverVeiculo(dataDevolucao, localDevolucao);
        System.out.println(aluguel.gerarComprovanteDeDevolucao());
    }

    public List<Aluguel> listarAlugueis() {
        return aluguelRepository.buscarTodos();
    }

    public List<Aluguel> buscarAlugueisPorCliente(Cliente cliente) {
        return aluguelRepository.buscarPorCliente(cliente);
    }

    public List<Aluguel> buscarAlugueisPorVeiculo(Veiculo veiculo) {
        return aluguelRepository.buscarPorVeiculo(veiculo);
    }

    public String gerarComprovanteDeAluguel(Aluguel aluguel) {
        return "Comprovante de Aluguel: \n"
                + "Cliente - " + cliente.getNome() + "\n"
                + "Veículo - " + veiculo.getModelo() + "\n"
                + "Data do Aluguel - " + dataRetirada + "\n"
                + "Local do Aluguel - " + localRetirada + "\n";
    }

    public String gerarComprovanteDeDevolucao() {
        return "Comprovante de Devolução: \n"
                + "Cliente - " + cliente.getNome() + "\n"
                + "Veículo - " + veiculo.getModelo() + "\n"
                + "Data do Aluguel - " + dataRetirada + "\n"
                + "Data de Devolução - " + dataDevolucao + "\n"
                + "Local de Devolução - " + localDevolucao + "\n"
                + "Total de Dias Alugados - " + calcularDiasAlugados() + "dias" + "\n";

    }
}