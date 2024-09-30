package utils;

import entities.usuario.Administrador;
import entities.usuario.PessoaFisica;
import entities.usuario.PessoaJuridica;
import entities.usuario.Usuario;

import java.util.List;

public class PaginationUtils {

    public static <T> void exibirPagina(List<T> lista, int pagina, int tamanhoPagina) {
        int inicio = (pagina - 1) * tamanhoPagina;
        int fim = Math.min(inicio + tamanhoPagina, lista.size());

        for (int i = inicio; i < fim; i++) {
            if(usuarios.get(i) instanceof Administrador administrador) {
                System.out.println(administrador.mostrarAdmin());
            } else if (usuarios.get(i) instanceof PessoaFisica pessoaFisica) {
                System.out.println(pessoaFisica.mostrarPF());
            } else if (usuarios.get(i) instanceof PessoaJuridica pessoaJuridica) {
                System.out.println(pessoaJuridica.mostrarPJ());;
            } else {
                System.out.println("Usuário de tipo desconhecido");
            }
        }
    }
}
