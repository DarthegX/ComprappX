package mycompra.app.logica;

import android.content.Context;

import java.util.ArrayList;

import mycompra.app.dao.ProductoDAO;
import mycompra.app.dao.TicketDAO;
import mycompra.app.modelo.Categoria;
import mycompra.app.modelo.Producto;
import mycompra.app.modelo.ProductoTicket;
import mycompra.app.modelo.Ticket;

public class Parser
{
    static ProductoDAO prodDao;
    static TicketDAO ticketDAO;

    public static int numProductosNuevos;

    public static ArrayList<Producto> productos;
    private static double precioTotalTicket;

    private static ArrayList<Integer> cantidades;
    private static ArrayList<String> nombres;
    private static ArrayList<Double> precios;

    private static int contadorPrecioKilo;


    public Parser(Context context){
        cantidades = new ArrayList<>();
        nombres = new ArrayList<>();
        precios = new ArrayList<>();

        prodDao = new ProductoDAO(context);
        ticketDAO = new TicketDAO(context);
    }

    // TODO: Implementar patron Estrategia

    public static void parseProducto(String prod)
    {
        if(!Character.isDigit(prod.charAt(2)))
        {
            cantidades.add(Integer.parseInt(String.valueOf(prod.charAt(0))));
            nombres.add(prod.substring(2));
        }
        else if (Character.isDigit(prod.charAt(2)))
        {
            if (prod.charAt(6) == 'k')
            {
                contadorPrecioKilo++;
                return;
            }

            precios.add(Double.parseDouble(prod));
        }
    }

    public static void createProductos()
    {
        int cantidad;
        String nombre;
        double precio;

        Ticket nuevoTicket = new Ticket();
        ticketDAO.insert(nuevoTicket);

        for(int i = 1; i <= numProductosNuevos; i++)
        {
            Producto nuevoProducto = new Producto();
            nuevoProducto.setId(prodDao.getLastProducto().getId() + i);

            cantidad = cantidades.get(i);
            nombre = nombres.get(i);
            precio = precios.get(contadorPrecioKilo + i);
            precioTotalTicket += precio;

            nuevoProducto.setCantidad(cantidad);
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setPrecio(precio);


            Categoria categoriaProd = ClasificadorCategoria.findCategoria(nombre);
            nuevoProducto.setIdCategoria(categoriaProd.getId());

            // TODO: Fecha caducidad

            productos.add(nuevoProducto);

            ProductoTicket nuevaRelacion = new ProductoTicket();
            nuevaRelacion.setIdTicket(nuevoTicket.getId());
            nuevaRelacion.setIdProducto(nuevoProducto.getId());
            nuevaRelacion.setCantidad(cantidad);

            GestorInventario.guardarProducto(nuevoProducto, nuevaRelacion);

            // TODO: Enviar precio ticket a gestor de mes
        }

        nuevoTicket.setPrecio(precioTotalTicket);
        ticketDAO.update(nuevoTicket);
    }
}
