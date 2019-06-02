package mycompra.app.iterador;

public interface Iterador<T> {
    boolean hasNext();
    T next();
    T actual();
    T get(int i);

}
