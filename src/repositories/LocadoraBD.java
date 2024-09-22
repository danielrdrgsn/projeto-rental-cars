package repositories;

import entities.Locadora;
import entities.agencia.Agencia;
import entities.usuario.Usuario;
import entities.veiculo.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LocadoraBD {

    public static void salvarDadosLocadora(Locadora locadora) throws IOException {
        salvarDadosEmArquivo(Locadora.getUsuarios(), "usuarios.txt");
        salvarDadosEmArquivo(Locadora.getAgencias(), "agencias.txt");
        salvarDadosEmArquivo(Locadora.getVeiculos(), "veiculos.txt");
    }

    public static void carregarUsuarios() throws IOException {
        try {
            Locadora.setUsuarios(carregarListaDeArquivo("usuarios.txt", Usuario.class));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void carregarAgencias() throws IOException {
        try {
            Locadora.setAgencias(carregarListaDeArquivo("agencias.txt", Agencia.class));
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static void carregarVeiculos() throws IOException {
        try {
            Locadora.setVeiculos(carregarListaDeArquivo("veiculos.txt", Veiculo.class));
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static <T> void salvarDadosEmArquivo(List<T> lista, String arquivo) throws IOException {
        File arquivoBD = new File(arquivo);
        if(!arquivoBD.exists()) {
            arquivoBD.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (T item : lista) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static <T> List<T> carregarListaDeArquivo(String arquivo, Class<T> classe) throws IOException {
        List<T> lista = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                lista.add(converterLinhaParaObjeto(linha, classe));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return lista;
    }

    private static <T> T converterLinhaParaObjeto(String linha, Class<T> classe) {
        if(classe.equals(Usuario.class)) {
            return classe.cast(Usuario.fromString(linha));
        } else if(classe.equals(Agencia.class)) {
            return classe.cast(Agencia.fromString(linha));
        } else if(classe.equals(Veiculo.class)) {
            return classe.cast(Veiculo.fromString(linha));
        } else {
            throw new IllegalArgumentException("Tipo desconhecido para conversão" + classe.getName());
        }
    }
}
