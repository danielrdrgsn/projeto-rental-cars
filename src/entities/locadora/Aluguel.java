package entities.locadora;

import entities.agencia.Agencia;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;
import services.AgenciaService;
import services.UsuarioService;
import services.VeiculoService;
import utils.NumberFormatter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Aluguel {

    private final Integer id;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataRetirada;
    private LocalDateTime dataDevolucao;
    private Agencia localRetirada;
    private Agencia localDevolucao;
    private BigDecimal valorAluguel;

    public Aluguel(Integer id, Cliente cliente, Veiculo veiculo, LocalDateTime dataRetirada, LocalDateTime dataDevolucao, Agencia localRetirada, Agencia localDevolucao, BigDecimal valorAluguel) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.localRetirada = localRetirada;
        this.localDevolucao = localDevolucao;
        this.valorAluguel = BigDecimal.ZERO;
    }

    public BigDecimal calcularValorTotal() {
        if(dataDevolucao == null) {
            dataDevolucao = LocalDateTime.now();
        }
        long qtidadeDias = calcularDiasAlugados();
        if(qtidadeDias < 0) qtidadeDias *= -1;

        switch (cliente.getTipoUsuario()) {
            case PF -> {
                return switch (veiculo.getTipo()) {
                    case CARRO -> {
                        if(qtidadeDias > 5) yield BigDecimal.valueOf((150.00 * qtidadeDias) * 0.95);
                        yield BigDecimal.valueOf((150.00 * qtidadeDias));
                    }
                    case MOTO -> BigDecimal.valueOf(100.00 * qtidadeDias);
                    case CAMINHAO -> BigDecimal.valueOf(200.00 * qtidadeDias);
                };
            }
            case PJ -> {
                return switch (veiculo.getTipo()) {
                    case CARRO -> {
                        if(qtidadeDias > 3) yield BigDecimal.valueOf((150.00 * qtidadeDias) * 0.90);
                        yield BigDecimal.valueOf((150.00 * qtidadeDias));
                    }
                    case MOTO -> BigDecimal.valueOf(100.00 * qtidadeDias);
                    case CAMINHAO -> BigDecimal.valueOf(200.00 * qtidadeDias);
                };
            }
            default -> {
                System.out.println("Tipo de cliente desconhecido.");
                return BigDecimal.ZERO;
            }
        }
    }

    public long calcularDiasAlugados() {
        return ChronoUnit.DAYS.between(dataRetirada, dataDevolucao);
    }

    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDateTime dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Agencia getLocalRetirada() {
        return localRetirada;
    }

    public void setLocalRetirada(Agencia localRetirada) {
        this.localRetirada = localRetirada;
    }

    public Agencia getLocalDevolucao() {
        return localDevolucao;
    }

    public void setLocalDevolucao(Agencia localDevolucao) {
        this.localDevolucao = localDevolucao;
    }

    public BigDecimal getValorAluguel() {
        return valorAluguel;
    }

    public void setValorAluguel(BigDecimal valorAluguel) {
        this.valorAluguel = valorAluguel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return Objects.equals(id, aluguel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String mostrarAluguel() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return  "ID: " + id + "\n" +
                "Cliente: " + cliente.getNome() + "\n" +
                "Veiculo: " + veiculo.getModelo() + "\n" +
                "Data de retirada: " + dataRetirada.format(formatter) + "\n" +
                "Local retirada: " + localRetirada.getNome() + "\n" +
                "Data de devolução: " + dataDevolucao.format(formatter) + "\n" +
                "Local devolucao: " + localDevolucao.getNome() + "\n" +
                "Valor do aluguel: R$ " + NumberFormatter.valorBigDecimalToString(valorAluguel) + "\n";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return    id + ";"
                + cliente.getEmail() + ";"
                + veiculo.getPlaca() + ";"
                + dataRetirada.format(formatter) + ";"
                + dataDevolucao.format(formatter) + ";"
                + localRetirada.getCodigo() + ";"
                + localDevolucao.getCodigo() + ";"
                + NumberFormatter.valorBigDecimalToString(valorAluguel);
    }

    public static Aluguel fromString(String linha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String[] partes = linha.split(";");
        Integer id = Integer.parseInt(partes[0]);
        String emailCliente = partes[1];
        String placaVeiculo = partes[2];
        LocalDateTime dataRetirada = LocalDateTime.parse(partes[3], formatter);
        LocalDateTime dataDevolucao = LocalDateTime.parse(partes[4], formatter);
        Integer codigoLocalRetirada = Integer.parseInt(partes[5]);
        Integer codigoLocalDevolucao = Integer.parseInt(partes[6]);
        BigDecimal valorAluguel = NumberFormatter.valorStringToBigDecimal(partes[7]);

        Cliente cliente = UsuarioService.buscarCliente(emailCliente);
        Veiculo veiculo = VeiculoService.buscarVeiculo(placaVeiculo).get();
        Agencia agenciaRetirada = AgenciaService.buscarAgencia(codigoLocalRetirada).get();
        Agencia agenciaDevolucao = AgenciaService.buscarAgencia(codigoLocalDevolucao).get();

        return new Aluguel(id, cliente, veiculo, dataRetirada, dataDevolucao, agenciaRetirada, agenciaDevolucao, valorAluguel);
    }
}
