package entities;

import entities.agencia.Agencia;
import entities.usuario.Cliente;
import entities.veiculo.Veiculo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Aluguel {

    private int ID;
    private Veiculo veiculoAlugado;
    private Agencia agenciaOrigem;
    private Agencia agenciaDestino;
    private Cliente cliente;
    private LocalDateTime dataRetirada;
    private LocalDateTime dataDevolucao;
    private BigDecimal valorTotal;

    public static Aluguel fromString(String linha) {
        //TODO
        return null;
    }

    /**
     * TODO: método construtor, métodos de acesso, equals + hashcode
     * TODO: toString, mostrarAluguel, fromString (para persistência da lista)
     */

}
