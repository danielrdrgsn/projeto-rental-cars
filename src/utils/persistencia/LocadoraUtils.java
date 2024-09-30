package utils.persistencia;

import entities.locadora.Aluguel;
import entities.locadora.Locadora;
import entities.agencia.Agencia;
import entities.usuario.Usuario;
import entities.veiculo.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LocadoraUtils {

    public static void salvarDadosLocadora() throws IOException {
        salvarDadosEmArquivo(Locadora.getUsuarios(), "usuarios.txt");
        salvarDadosEmArquivo(Locadora.getAgencias(), "agencias.txt");
        salvarDadosEmArquivo(Locadora.getVeiculos(), "veiculos.txt");
        salvarDadosEmArquivo(Locadora.getAlugueis(), "alugueis.txt");
    }

    public static void carregarDadosLocadora() throws IOException {
        Locadora.setUsuarios(carregarListaDeArquivo("usuarios.txt", Usuario.class));
        Locadora.setAgencias(carregarListaDeArquivo("agencias.txt", Agencia.class));
        Locadora.setVeiculos(carregarListaDeArquivo("veiculos.txt", Veiculo.class));
        Locadora.setAlugueis(carregarListaDeArquivo("alugueis.txt", Aluguel.class));
    }

    private static <T> void salvarDadosEmArquivo(List<T> lista, String arquivo) throws IOException {

        verificaBancoDeDados(arquivo);

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

        verificaBancoDeDados(arquivo);

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
        } else if(classe.equals(Aluguel.class)) {
            return classe.cast(Aluguel.fromString(linha));
        } else {
            throw new IllegalArgumentException("Tipo desconhecido para conversão" + classe.getName());
        }
    }

    private static void verificaBancoDeDados(String arquivo) throws IOException {
        File arquivoBD = new File(arquivo);
        if(!arquivoBD.exists()) {
            arquivoBD.createNewFile();
        }
    }
}
