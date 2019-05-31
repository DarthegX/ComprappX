package mycompra.app.controlador;


import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mycompra.app.R;
import mycompra.app.adaptersRecycler.AdapterProductos;
import mycompra.app.adaptersRecycler.RecyclerItemClickListener;
import mycompra.app.dao.CategoriaDAO;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.modelo.Categoria;
import mycompra.app.modelo.Producto;


/**
 * A simple {@link Fragment} subclass.
 */
public class Productos extends Fragment {

    ArrayList<String> listDatosProd;
    ArrayList<String> listProduct;
    ArrayList<String> listCatProd;
    RecyclerView recyclerView;
    ArrayList<Producto> listaProductos;

    public Productos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        recyclerView = view.findViewById(R.id.RecyclerIdProd);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaProd();

        AdapterProductos adapter = new AdapterProductos(listDatosProd,listProduct,listCatProd);

        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity().getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();

                bundle.putString("fragmentAnterior", "Productos");
                bundle.putString("idProducto", String.valueOf(listaProductos.get(position).getId()));

                DetalleProdInventario detalleProdInventario = new DetalleProdInventario();
                detalleProdInventario.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, detalleProdInventario);
                ft.commit();
            }
        }));

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton buttonNuevosPoductos = view.findViewById(R.id.buttonNuevoProducto_prod);
        buttonNuevosPoductos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                bundle.putString("fragmentAnterior", "Productos");

                NuevoProducto nuevoProducto = new NuevoProducto();
                nuevoProducto.setArguments(bundle);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame, nuevoProducto);
                ft.commit();
            }
        });

        return view;
    }

    private void llenarListaProd() {
        ProductoDAO productoDAO = new ProductoDAO(getActivity().getApplicationContext());
        CategoriaDAO categoriaDAO = new CategoriaDAO(getActivity().getApplicationContext());

        ArrayList<Categoria> listaCategorias = categoriaDAO.getCategoriaList();

        listaProductos = productoDAO.getProductoList();

        listDatosProd = new ArrayList<String>();
        listProduct = new ArrayList<String>();
        listCatProd = new ArrayList<String>();

        for(int i = 0; i < listaProductos.size(); i++){
            listDatosProd.add(String.valueOf(listaProductos.get(i).getCantidad()));
            listProduct.add(listaProductos.get(i).getNombre());
            if (listaProductos.get(i).getIdCategoria() != 0) {
                listCatProd.add(listaCategorias.get(listaProductos.get(i).getIdCategoria() - 1).getNombre());
            }
            else {
                listCatProd.add("Sin categoria");
            }
        }
    }
}
