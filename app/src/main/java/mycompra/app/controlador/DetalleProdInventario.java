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
import android.widget.TextView;

import mycompra.app.R;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.modelo.Producto;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleProdInventario extends Fragment implements AdapterView.OnItemSelectedListener {

    private Producto producto;
    private String idProducto;
    private String anteriorFragment;
    private ProductoDAO productoDAO;
    private EditText editTextPrecio;
    private EditText editTextCantidad;
    private EditText editTextCaducidad;
    private Spinner spinner;
    private TextView textViewNombre;
    private Button btnBorrar;
    private Button btnActualizar;
    private ArrayAdapter<CharSequence> adapter;

    public DetalleProdInventario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_prod_inventario, container, false);

        editTextPrecio = view.findViewById(R.id.editTextPrecioDetalle);
        editTextCantidad = view.findViewById(R.id.textViewCantidadDetalle);
        editTextCaducidad = view.findViewById(R.id.editTextCaducidadDetalle);
        textViewNombre = view.findViewById(R.id.textNombreProductoFragmentDetalle);
        spinner = view.findViewById(R.id.spinnerDetalle);

        idProducto = getArguments().getString("idProducto");
        anteriorFragment = getArguments().getString("fragmentAnterior");

        adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.inventarios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        productoDAO = new ProductoDAO(getActivity().getApplicationContext());

        producto = productoDAO.getProductoById(Integer.valueOf(idProducto));

        textViewNombre.setText(producto.getNombre());
        editTextPrecio.setText(String.valueOf(producto.getPrecio()));
        editTextCantidad.setText(String.valueOf(producto.getCantidad()));
        if (producto.getCaducidad() != null) {
            editTextCaducidad.setText(producto.getCaducidad());
        }
        switch (producto.getIdInventario()){
            case 1:
                spinner.setSelection(2);
                break;
            case 2:
                spinner.setSelection(0);
                break;
            case 3:
                spinner.setSelection(1);
                break;
        }

        btnBorrar = view.findViewById(R.id.btnBorrarDetalle);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productoDAO.delete(producto.getId());
                devolverAFragmentAnterior();
            }
        });

        btnActualizar = view.findViewById(R.id.btnActualizarDetalle);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextPrecio.getText().toString().equalsIgnoreCase("")) {
                    producto.setPrecio(Double.parseDouble(editTextPrecio.getText().toString()));
                }
                if (!editTextCantidad.getText().toString().equalsIgnoreCase("") && !editTextCantidad.getText().toString().equalsIgnoreCase("0")) {
                    producto.setCantidad(Integer.parseInt(editTextCantidad.getText().toString()));
                }
                if (!editTextCaducidad.getText().toString().equalsIgnoreCase("")) {
                    producto.setCaducidad(editTextCaducidad.getText().toString());
                }
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
                productoDAO.update(producto);
                devolverAFragmentAnterior();
            }
        });

        return view;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void devolverAFragmentAnterior() {
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
                break;
        }
        ft.commit();
    }

}
