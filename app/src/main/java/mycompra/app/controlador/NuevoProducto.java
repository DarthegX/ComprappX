package mycompra.app.controlador;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mycompra.app.R;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.modelo.Producto;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoProducto extends Fragment implements AdapterView.OnItemSelectedListener {

    private ProductoDAO productoDAO;
    private String anteriorFragment;
    private EditText editTextNombre;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;

    public NuevoProducto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nuevo_producto, container, false);

        anteriorFragment = getArguments().getString("fragmentAnterior");

        productoDAO = new ProductoDAO(getActivity().getApplicationContext());

        spinner = view.findViewById(R.id.spinnerNuevoProducto);

        adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.inventarios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button btnCancelar = (Button) view.findViewById(R.id.cancelarNuevoProducto);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                switch (anteriorFragment.toLowerCase()) {
                    case "nevera":
                        ft.replace(R.id.frame, new Nevera());
                        break;
                    case "congelador":
                        ft.replace(R.id.frame, new Congelador());
                        break;
                    case "despensa":
                        ft.replace(R.id.frame, new Despensa());
                        break;
                    case "productos":
                        ft.replace(R.id.frame, new Productos());
                }
                ft.commit();
            }

        });

        Button btnAnyadir = view.findViewById(R.id.btnAnyadirFragmentNuevoProducto);
        editTextNombre = view.findViewById(R.id.editTextNombreNuevoProducto);

        btnAnyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextNombre.getText().toString().equalsIgnoreCase("")) {
                    Producto producto = new Producto();
                    producto.setNombre(String.valueOf(editTextNombre.getText()));
                    switch (anteriorFragment.toLowerCase()) {
                        case "nevera":
                            producto.setIdInventario(2);
                            break;
                        case "congelador":
                            producto.setIdInventario(3);
                            break;
                        case "despensa":
                            producto.setIdInventario(1);
                            break;
                        case "productos":
                            switch (spinner.getSelectedItemPosition()) {
                                case 0:
                                    producto.setIdInventario(2);
                                    break;
                                case 1:
                                    producto.setIdInventario(3);
                                    break;
                                case 2:
                                    producto.setIdInventario(1);
                                    break;
                            }
                            break;
                    }
                    productoDAO.insert(producto);
                    editTextNombre.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "Producto añadido correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
