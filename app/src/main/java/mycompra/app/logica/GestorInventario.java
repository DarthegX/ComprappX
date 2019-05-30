package mycompra.app.logica;
import android.content.Context;

import java.util.ArrayList;

import mycompra.app.dao.CategoriaDAO;
import mycompra.app.dao.InventarioDAO;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.dao.ProductoTicketDAO;
import mycompra.app.modelo.Categoria;
import mycompra.app.modelo.Inventario;
import mycompra.app.modelo.Producto;
import mycompra.app.modelo.ProductoTicket;

public class GestorInventario
{
    private static InventarioDAO invDao;
    private static ProductoDAO productoDAO;
    private static CategoriaDAO catDAO;
    private static ProductoTicketDAO prodTicketDAO;

    private static ArrayList<Inventario> inventarios;
    private static ArrayList<Categoria> categorias;

    private static ControlCaducidad caducidades;


    public GestorInventario(Context context)
    {
        invDao = new InventarioDAO(context);
        productoDAO = new ProductoDAO(context);
        prodTicketDAO = new ProductoTicketDAO(context);

        inventarios = invDao.getInventarioList();
    }

    public static void guardarProducto(Producto prod, ProductoTicket relacion)
    {
        for (int i = 0; i < inventarios.size(); i++)
        {
            categorias = catDAO.getCategoriaListByInventario(inventarios.get(i).getId());

            for (int j = 0; j < categorias.size(); j++)
            {
                if (prod.getNombre().contains(categorias.get(i).getNombre()))
                {
                    prod.setIdInventario(categorias.get(i).getIdInventario());
                    commitProducto(prod, relacion);
                    return;
                }
            }
        }
    }

    private static void commitProducto(Producto prod, ProductoTicket relacion)
    {
        productoDAO.insert(prod);
        prodTicketDAO.insert(relacion);
    }

    /*public ArrayList<Producto> getProductos(Inventario inventario)
    {
        return inventario.getProductos();
    }*/
}
