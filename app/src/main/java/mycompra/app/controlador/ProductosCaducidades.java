package mycompra.app.controlador;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import mycompra.app.R;
import mycompra.app.adaptersRecycler.AdapterProductos;
import mycompra.app.adaptersRecycler.RecyclerItemClickListener;
import mycompra.app.dao.CategoriaDAO;
import mycompra.app.dao.InventarioDAO;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.iterador.Iterador;
import mycompra.app.logica.ControlCaducidad;
import mycompra.app.modelo.Categoria;
import mycompra.app.modelo.Producto;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductosCaducidades extends Fragment {

    ArrayList<Producto> prodsCaducados;
    ArrayList<Producto> prodsCaducaHoy;
    ArrayList<Producto> prodsCaduProxima;

    ArrayList<String> listInventarioProd;
    ArrayList<String> listProduct;
    ArrayList<String> listFechaProd;

    RecyclerView recyclerView;
    Iterador<Producto> listaProductos;

    public ProductosCaducidades() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productos_caducidad, container, false);
        
        recyclerView = view.findViewById(R.id.RecyclerIdProd);

        getActivity().setTitle("Caducidades");

        llenarListaProd();

        AdapterProductos adapter = new AdapterProductos(listInventarioProd,listProduct,listFechaProd);

        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();

                bundle.putString("fragmentAnterior", "Productos");
                bundle.putString("idProducto", String.valueOf(listaProductos.get(position).getId()));

                DetalleProdInventario detalleProdInventario = new DetalleProdInventario();
                detalleProdInventario.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, detalleProdInventario).addToBackStack(null);
                ft.commit();
            }
        }));*/

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        return view;
    }

    private void llenarListaProd() {
        ProductoDAO productoDAO = new ProductoDAO(getActivity().getApplicationContext());
        //CategoriaDAO categoriaDAO = new CategoriaDAO(getActivity().getApplicationContext());
        InventarioDAO inventarioDAO = new InventarioDAO(getActivity().getApplicationContext());

        //Iterador<Categoria> listaCategorias = categoriaDAO.getCategoriaList();

        //listaProductos = productoDAO.getProductoList();

        //prodsCaducados = ControlCaducidad.prodsCaducados;
        //prodsCaducaHoy = ControlCaducidad.prodsCaducaHoy;
        //prodsCaduProxima = ControlCaducidad.prodsCaduProxima;

        if (!ControlCaducidad.prodsCaducados.isEmpty())
        {
            for (int i = 0; i < ControlCaducidad.prodsCaducados.size(); i++)
            {
                listInventarioProd.add(inventarioDAO.getInventarioById(ControlCaducidad.prodsCaducados.get(i).getIdInventario()).getNombre());
                listProduct.add(ControlCaducidad.prodsCaducados.get(i).getNombre());
                listFechaProd.add(ControlCaducidad.prodsCaducados.get(i).getCaducidad());
            }
        }

        if (!ControlCaducidad.prodsCaducaHoy.isEmpty())
        {
            for (int i = 0; i < ControlCaducidad.prodsCaducaHoy.size(); i++)
            {
                listInventarioProd.add(inventarioDAO.getInventarioById(ControlCaducidad.prodsCaducaHoy.get(i).getIdInventario()).getNombre());
                listProduct.add(ControlCaducidad.prodsCaducaHoy.get(i).getNombre());
                listFechaProd.add(ControlCaducidad.prodsCaducaHoy.get(i).getCaducidad());
            }
        }

        if (!ControlCaducidad.prodsCaduProxima.isEmpty())
        {
            for (int i = 0; i < ControlCaducidad.prodsCaduProxima.size(); i++)
            {
                listInventarioProd.add(inventarioDAO.getInventarioById(ControlCaducidad.prodsCaduProxima.get(i).getIdInventario()).getNombre());
                listProduct.add(ControlCaducidad.prodsCaduProxima.get(i).getNombre());
                listFechaProd.add(ControlCaducidad.prodsCaduProxima.get(i).getCaducidad());
            }
        }
    }
}
