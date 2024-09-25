package entities.locadora;

import entities.usuario.Cliente;
import entities.veiculo.Veiculo;

import java.time.LocalDate;

public class Aluguel {

    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private String localAluguel;
    private String localDevolucao;
    private boolean isDevolvido;


    public Aluguel(Cliente cliente, Veiculo veiculo, LocalDate dataAluguel, String localAluguel){
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataAluguel = dataAluguel;
        this.localAluguel = localAluguel;
        this.isDevolvido = false;
    }




    public void alugarVeiculo(){
        if(!veiculo.isDisponivel()){

            throw new IllegalArgumentException("Este veículo não está disponível!");

        }
        veiculo.alteraDisponibilidade();
    }



    public void devolverVeiculo(LocalDate dataDevolucao, String localDevolucao){
        this.dataDevolucao = dataDevolucao;
        this.localDevolucao = localDevolucao;
        this.isDevolvido = true;
        veiculo.alteraDisponibilidade();
    }



    public String gerarComprovanteDeAluguel(){

        return "Comprovante de Aluguel: \n"
                + "Cliente - " + cliente.getNome() + "\n"
                + "Veículo - " + veiculo.getModelo() + "\n"
                + "Data do Aluguel - " + dataAluguel + "\n"
                + "Local do Aluguel - " + localAluguel + "\n"
                + "Devolução - " + (isDevolvido ? "Sim" : "Não") + "\n";

    }

    public String gerarComprovanteDeDevolucao(){
        return "Comprovante de Devolução: \n"
                + "Cliente - " + cliente.getNome() + "\n"
                + "Veículo - " + veiculo.getModelo() + "\n"
                + "Data do Aluguel - " + dataAluguel + "\n"
                + "Data de Devolução - " + dataDevolucao + "\n"
                + "Local de Devolução - " + localDevolucao + "\n"
                + "Total de Dias Alugados - " + calcularDiasAlugados() + "dias" + "\n";

    }

    public long calcularDiasAlugados(){
        if(dataDevolucao != null){
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


    public LocalDate getDataAluguel() {
        return dataAluguel;
    }


    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }



    public String getLocalAluguel() {
        return localAluguel;
    }



    public String getLocalDevolucao() {
        return localDevolucao;
    }



    public boolean isDevolvido() {
        return isDevolvido;
    }


}
