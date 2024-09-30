package repositories;

import entities.agencia.Agencia;
import entities.usuario.Usuario;

import java.util.List;

public interface Repositorio<T, I> {

    void adicionar(T t);
    void editar(Usuario agencia, String email, Object usuario);
    T remover(T t);

    void editar(Agencia agencia, String email);

    Agencia remover(Integer id);

    T buscar(String email);
    List<T> listar();

}
