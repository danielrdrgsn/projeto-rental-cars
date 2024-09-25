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
