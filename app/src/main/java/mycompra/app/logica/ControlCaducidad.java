package mycompra.app.logica;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import mycompra.app.dao.ProductoDAO;
import mycompra.app.modelo.Producto;

public class ControlCaducidad
{
    public static ArrayList<Producto> prodsCaducados;
    public static ArrayList<Producto> prodsCaducaHoy;
    public static ArrayList<Producto> prodsCaduProxima;

    public static int MAX_CAD_DIAS = 2;
    private static ProductoDAO productoDAO;
    private static ArrayList<Producto> productos;


    public ControlCaducidad(Context context)
    {
        productoDAO = new ProductoDAO(context);
    }

    public static void checkCaducidades()
    {
        int i;
        productos = productoDAO.getProductoList();
        for (i = 0; i < productos.size(); i++)
        {
            checkCaducidad(productos.get(i));
        }
    }

    public static void checkCaducidad(Producto prod)
    {
        String caducidad = prod.getCaducidad();
        String dia = caducidad.substring(0,2);
        String mes = caducidad.substring(4,5);

        if (Integer.parseInt(dia) > Calendar.DATE && Integer.parseInt(mes) >= Calendar.MONTH)
        {
            // Alarma PRODUCTO CADUCADO
            prodsCaducados.add(prod);
        }
        else if ((Integer.parseInt(dia) == Calendar.DATE && Integer.parseInt(mes) == Calendar.MONTH))
        {
            // Alarma PRODUCTO CADUCA HOY
            prodsCaducaHoy.add(prod);
        }
        else if ((Calendar.DATE - (Integer.parseInt(dia)) == MAX_CAD_DIAS && Integer.parseInt(mes) == Calendar.MONTH))
        {
            // Alarma PRODUCTO CADUCA PRONTO
            prodsCaduProxima.add(prod);
        }
    }


}
