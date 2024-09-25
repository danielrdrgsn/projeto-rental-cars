package repositories;

import entities.locadora.Aluguel;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class AluguelRepositoryImplementacao implements AluguelRepository{


    private List<Aluguel> alugueis;

    public AluguelRepositoryImplementacao(List<Aluguel> aluguels) {
        this.alugueis = new ArrayList<>();
    }

    @Override
    public void salvarAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    @Override
    public List<Aluguel> buscarTodos() {
        return new ArrayList<>(alugueis);
    }

    @Override
    public List<Aluguel> buscarPorCliente(Cliente cliente) {
        List<Aluguel> resultados = new ArrayList<>();

        for(Aluguel aluguel : alugueis){
            if(aluguel.getCliente().equals(cliente)){
                resultados.add(aluguel);
            }
        }

        return resultados;
    }



    @Override
    public List<Aluguel> buscarPorVeiculo(Veiculo veiculo) {
        List<Aluguel> resultados = new ArrayList<>();

        for(Aluguel aluguel : alugueis){
            if(aluguel.getVeiculo().equals(veiculo)){
                resultados.add(aluguel);
            }
        }
        return resultados;
    }
}
