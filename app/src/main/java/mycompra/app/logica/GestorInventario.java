package mycompra.app.logica;
import android.content.Context;

import java.util.ArrayList;

import mycompra.app.dao.InventarioDAO;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.modelo.Inventario;
import mycompra.app.modelo.Producto;

public class GestorInventario
{
    private static InventarioDAO invDao;
    private static ProductoDAO productoDAO;

    private static ArrayList<Inventario> inventarios;
    private static ControlCaducidad caducidades;


    public GestorInventario(Context context)
    {
        invDao = new InventarioDAO(context);
        productoDAO = new ProductoDAO(context);

        inventarios = invDao.getInventarioList();
    }

    public boolean guardarProductos(ArrayList<Producto> prods)
    {
        Producto prod;

        for (int i = 0; i < prods.size(); i++)
        {
            prod = prods.get(i);

            if (prod.getIdCategoria() == NEVERA.getCategorias().get(i).getId())
            {
                NEVERA.putProducto(prod);
                return true;
            }
            else if (prod.getIdCategoria() == CONGELADOR.getCategorias().get(i).getId())
            {
                CONGELADOR.putProducto(prod);
                return true;
            }
            else if (prod.getIdCategoria() == DESPENSA.getCategorias().get(i).getId())
            {
                DESPENSA.putProducto(prod);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Producto> getProductos(Inventario inventario)
    {
        return inventario.getProductos();
    }
}
