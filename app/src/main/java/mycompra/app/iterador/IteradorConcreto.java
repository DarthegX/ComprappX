package mycompra.app.iterador;
import java.util.ArrayList;
import java.util.List;

public class IteradorConcreto<T> implements Iterador {

    private List<T> list;
    private int cursor = 0;

    public IteradorConcreto(List<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return cursor != list.size();
    }

    @Override
    public T next() {
        T obj = null;
        if (hasNext()) {
            obj =  list.get(cursor++);
        }
        return obj;
    }
}
