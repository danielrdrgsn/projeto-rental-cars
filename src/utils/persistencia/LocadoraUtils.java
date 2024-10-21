package utils.persistencia;

import entities.locadora.Aluguel;
import entities.locadora.Locadora;
import entities.agencia.Agencia;
import entities.usuario.Usuario;
import entities.veiculo.Veiculo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> linhas = lista.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        Files.write(Paths.get(arquivo), linhas, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING);
    }

    private static <T> List<T> carregarListaDeArquivo(String arquivo, Class<T> classe) throws IOException {
        verificaBancoDeDados(arquivo);

        return Files.lines(Paths.get(arquivo), StandardCharsets.UTF_8)
                .map(linha -> converterLinhaParaObjeto(linha, classe))
                .collect(Collectors.toList());
    }

    private static <T> T converterLinhaParaObjeto(String linha, Class<T> classe) {
        if (classe.equals(Usuario.class)) {
            return classe.cast(Usuario.fromString(linha));
        } else if (classe.equals(Agencia.class)) {
            return classe.cast(Agencia.fromString(linha));
        } else if (classe.equals(Veiculo.class)) {
            return classe.cast(Veiculo.fromString(linha));
        } else if (classe.equals(Aluguel.class)) {
            return classe.cast(Aluguel.fromString(linha));
        } else {
            throw new IllegalArgumentException("Tipo desconhecido para conversão: " + classe.getName());
        }
    }

    private static void verificaBancoDeDados(String arquivo) throws IOException {
        Path path = Paths.get(arquivo);
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }
}
