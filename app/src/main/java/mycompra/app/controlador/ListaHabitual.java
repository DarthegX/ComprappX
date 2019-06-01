package mycompra.app.controlador;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import mycompra.app.R;
import mycompra.app.adaptersRecycler.AdapterListaHabitual;
import mycompra.app.dao.ListaDAO;
import mycompra.app.dao.ProductoListaDAO;
import mycompra.app.modelo.Lista;
import mycompra.app.modelo.Producto;
import mycompra.app.modelo.ProductoLista;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaHabitual extends Fragment {

    RecyclerView recycler;
    ArrayList<String> listCheckBox;
    private String idLista;
    private TextView textViewNombreListaHabitual;
    private ImageButton imageButtonEscaner;
    private Lista lista;
    private ListaDAO listaDAO;

    public ListaHabitual() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_habitual, container, false);

        idLista = getArguments().getString("idLista");

        textViewNombreListaHabitual = view.findViewById(R.id.textViewNombreListaHabitual);
        imageButtonEscaner = view.findViewById(R.id.imageButtonEscaner);
        recycler = view.findViewById(R.id.RecyclerListaHabitualId);
        listaDAO = new ListaDAO(getActivity().getApplicationContext());
        lista = listaDAO.getListaById(Integer.parseInt(idLista));

        llenarLista();

        AdapterListaHabitual adapter = new AdapterListaHabitual(listCheckBox);

        recycler.setAdapter(adapter);

        recycler.setItemAnimator(new DefaultItemAnimator());

        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(),
                ((LinearLayoutManager) recycler.getLayoutManager()).getOrientation());
        recycler.addItemDecoration(dividerItemDecoration);

        FloatingActionButton eliminarProd = view.findViewById(R.id.btnBorrarListaHabitual);
        eliminarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaDAO.delete(lista.getId());
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame, new Listas());
                fr.commit();
            }
        });

        imageButtonEscaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        FloatingActionButton nuevoProdLista = view.findViewById(R.id.btnAnyadirProductoAListaHabitual);
        nuevoProdLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame, new NuevoProductoLista()).addToBackStack(null);
                fr.commit();
            }
        });

        return view;
    }

    private void llenarLista() {
        ProductoListaDAO productoListaDAO = new ProductoListaDAO(getActivity().getApplicationContext());
        ArrayList<Producto> listaProductos = productoListaDAO.getProductoListFromListaHabitual(lista.getId());

        listCheckBox = new ArrayList<String>();

        for(int i = 0; i < listaProductos.size(); i++){
            listCheckBox.add(listaProductos.get(i).getNombre());
        }
    }
}
