package mycompra.app.logica;

import android.content.Context;

import java.util.ArrayList;

import mycompra.app.dao.CategoriaDAO;
import mycompra.app.dao.TagDAO;
import mycompra.app.modelo.Categoria;
import mycompra.app.modelo.Producto;
import mycompra.app.modelo.Tag;

public class ClasificadorCategoria {
    //private Categoria tags;
    private static CategoriaDAO categoriaDAO;
    private static TagDAO tagDAO;

    private static ArrayList<Categoria> categorias;
    private static ArrayList<Tag> tagsCategoria;

    public ClasificadorCategoria(Context context)
    {
        categoriaDAO = new CategoriaDAO(context);
        tagDAO = new TagDAO(context);
        categorias = categoriaDAO.getCategoriaList();
    }


    public static Categoria findCategoria(String nombreProd)
    {
        for (int i = 0; i < categorias.size(); i++) // Recorre categorias
        {
            Categoria categoriaActual = categorias.get(i);

            tagsCategoria = tagDAO.getTagListByCategoria(categoriaActual.getId());

            for (int j = 0; j < tagsCategoria.size(); j++) // Recorre tags de la categoria actual
            {
                if (tagsCategoria.get(i).getNombre().contains(nombreProd)) {
                    return categoriaActual;
                }
            }
        }

        return null;
    }
}