package entities.locadora;

import entities.agencia.Agencia;
import entities.usuario.Usuario;
import entities.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.List;

public class Locadora {

    private String nome;
    private static List<Usuario> usuarios;
    private static List<Agencia> agencias;
    private static List<Veiculo> veiculos;

    public Locadora(String nome) {
        this.nome = nome;
        usuarios = new ArrayList<>();
        agencias = new ArrayList<>();
        veiculos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public static List<Agencia> getAgencias() {
        return agencias;
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(List<Usuario> usuarios) {
        Locadora.usuarios = usuarios;
    }

    public static void setAgencias(List<Agencia> agencias) {
        Locadora.agencias = agencias;
    }

    public static void setVeiculos(List<Veiculo> veiculos) {
        Locadora.veiculos = veiculos;
    }
}
