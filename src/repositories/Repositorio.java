package repositories;

import java.util.List;

public interface Repositorio<T, I> {

    void salvar(T t);
    void atualizar(T t);
    T remover(T t);
    T buscarPorId(I id);
    List<T> buscarTodos();

}
