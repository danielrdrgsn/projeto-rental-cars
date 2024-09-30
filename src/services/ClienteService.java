package services;

import entities.veiculo.Veiculo;

public class ClienteService {

    private static final VeiculoService veiculoService = new VeiculoService();
    private static final AluguelService aluguelService = new AluguelService();

    public void alugarVeiculo(Integer idVeiculo) {
        Veiculo veiculo = veiculoService.buscar(idVeiculo);
        if(veiculo != null || !veiculo.isDisponivel()) {

        }
        if (!veiculo.isDisponivel()) {
            throw new IllegalArgumentException("Este veículo não está disponível!");
        }
        veiculo.alteraDisponibilidade();
        aluguelService.mostrarComprovanteAlguel();
    }

    public void devolverVeiculo(Veiculo veiculo) {
        this.veiculo.alteraDisponibilidade();
    }
}
