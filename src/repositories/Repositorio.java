package repositories;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T, I> {

    void adicionar(T t);
    void editar(T t, I id);
    T remover(T t);
    Optional<T> buscar(I id);
    List<T> listar();

}
