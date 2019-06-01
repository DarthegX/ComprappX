package mycompra.app.iterador;

public interface Agregado<T> {
    void add(T obj);

    void delete(T obj);

    Iterador<T> iterador();
}
