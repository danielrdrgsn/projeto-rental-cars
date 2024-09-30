package entities.locadora;

import entities.agencia.Agencia;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Aluguel {

    private Integer id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataRetirada;
    private LocalDateTime dataDevolucao;
    private Agencia localRetirada;
    private Agencia localDevolucao;
    private BigDecimal valorAluguel;

    public Aluguel(Integer id, Cliente cliente, Veiculo veiculo,
                   LocalDateTime dataRetirada, LocalDateTime dataDevolucao,
                   Agencia localRetirada, Agencia localDevolucao,
                   BigDecimal valorAluguel) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = LocalDateTime.now();
        this.dataDevolucao = dataDevolucao;
        this.localRetirada = localRetirada;
        this.localDevolucao = localDevolucao;
        this.valorAluguel = calcularValorAluguel();
    }

    private BigDecimal calcularValorAluguel() {
        return new BigDecimal(0);
    }

    public void alugarVeiculo() {
        if (!veiculo.isDisponivel()) {
            throw new IllegalArgumentException("Este veículo não está disponível!");
        }
        veiculo.alteraDisponibilidade();
    }

    public void devolverVeiculo(Veiculo veiculo) {
        this.veiculo.alteraDisponibilidade();
    }

    public String gerarComprovanteDeAluguel() {
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

    public long calcularDiasAlugados() {
        if (dataDevolucao != null) {
            return dataDevolucao.toEpochDay() - dataAluguel.toEpochDay();
        }
        return 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getDataAluguel() {
        return dataRetirada;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public Agencia getLocalAluguel() {
        return localRetirada;
    }

    public Agencia getLocalDevolucao() {
        return localDevolucao;
    }
}
