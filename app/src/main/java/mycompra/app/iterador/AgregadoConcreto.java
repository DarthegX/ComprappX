package mycompra.app.iterador;

import java.util.ArrayList;
import java.util.List;

public class AgregadoConcreto<T> implements Agregado<T> {

    private List<T> lista = new ArrayList<>();


    @Override
    public void add(T obj) {
        lista.add(obj);
    }

    @Override
    public void delete(T obj) {
        lista.remove(obj);
    }

    @Override
    public Iterador<T> iterador() {
        return new IteradorConcreto<T>(lista);
    }

}
