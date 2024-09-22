package repositories;

import entities.agencia.Agencia;
import entities.Locadora;
import entities.usuario.Usuario;
import entities.veiculo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LocadoraBD {

    public static void salvarDadosLocadora(Locadora locadora) throws IOException {
        salvarListaEmArquivo(Locadora.getUsuarios(), "usuarios.txt");
        salvarListaEmArquivo(Locadora.getAgencias(), "agencias.txt");
        salvarListaEmArquivo(Locadora.getVeiculos(), "veiculos.txt");
    }

    public static void carregarDadosLocadora() throws IOException {
        Locadora.setUsuarios(carregarListaDeArquivo("usuarios.txt", Usuario.class));
        Locadora.setAgencias(carregarListaDeArquivo("agencias.txt", Agencia.class));
        Locadora.setVeiculos(carregarListaDeArquivo("veiculos.txt", Veiculo.class));
    }

    private static <T> void salvarListaEmArquivo(List<T> lista, String arquivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (T item : lista) {
                writer.write(item.toString());
                writer.newLine();
            }
        }
    }

    private static <T> List<T> carregarListaDeArquivo(String arquivo, Class<T> classe) throws IOException {
        List<T> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                lista.add(converterLinhaParaObjeto(linha, classe));
            }
        }
        return lista;
    }

    private static <T> T converterLinhaParaObjeto(String linha, Class<T> classe) {
        if(classe.equals(Usuario.class)) {
            // TODO
        } else if(classe.equals(Agencia.class)) {
            return classe.cast(Agencia.fromString(linha));
        } else if(classe.equals(Veiculo.class)) {
            return classe.cast(Veiculo.fromString(linha));
        } else {
            throw new IllegalArgumentException("Tipo desconhecido para conversão" + classe.getName());
        }
        return null;
    }

    private Usuario converteLinhaParaUsuario(String linha) {
        String[] split = linha.split(",");


        return null;
    }
}
