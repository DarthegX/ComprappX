package mycompra.app.controlador;


import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import mycompra.app.R;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.modelo.Producto;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleProdInventario extends Fragment implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private Producto producto;
    private String idProducto;
    private String anteriorFragment;
    private ProductoDAO productoDAO;
    private EditText editTextNombre;
    private EditText editTextPrecio;
    private EditText editTextCantidad;
    private EditText editTextCaducidad;
    private Spinner spinnerInventario;
    private Spinner spinnerCategoria;
    private TextView textViewNombre;
    private Button btnBorrar;
    private Button btnActualizar;
    private ArrayAdapter<CharSequence> adapterInventario;
    private ArrayAdapter<CharSequence> adapterCategoria;
    private View view;
    private String fecha;

    public DetalleProdInventario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalle_prod_inventario, container, false);

        editTextNombre = view.findViewById(R.id.editTextNombreDetalle);
        editTextPrecio = view.findViewById(R.id.editTextPrecioDetalle);
        editTextCantidad = view.findViewById(R.id.textViewCantidadDetalle);
        editTextCaducidad = view.findViewById(R.id.editTextCaducidadDetalle);
        textViewNombre = view.findViewById(R.id.textNombreProductoFragmentDetalle);
        spinnerInventario = view.findViewById(R.id.spinnerInventarioDetalle);
        spinnerCategoria = view.findViewById(R.id.spinnerCategoriaDetalle);

        idProducto = getArguments().getString("idProducto");
        anteriorFragment = getArguments().getString("fragmentAnterior");

        adapterInventario = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.inventarios, android.R.layout.simple_spinner_item);
        adapterInventario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerInventario.setAdapter(adapterInventario);
        spinnerInventario.setOnItemSelectedListener(this);

        adapterCategoria = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerCategoria.setOnItemSelectedListener(this);

        editTextCaducidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        productoDAO = new ProductoDAO(getActivity().getApplicationContext());

        producto = productoDAO.getProductoById(Integer.valueOf(idProducto));

        textViewNombre.setText(producto.getNombre());
        editTextNombre.setText(producto.getNombre());
        editTextPrecio.setText(String.valueOf(producto.getPrecio()));
        editTextCantidad.setText(String.valueOf(producto.getCantidad()));
        editTextCaducidad.setText(producto.getCaducidad());
        switch (producto.getIdCategoria()) {
            case 1:
                spinnerCategoria.setSelection(0);
                break;
            case 2:
                spinnerCategoria.setSelection(1);
                break;
            case 3:
                spinnerCategoria.setSelection(2);
                break;
            case 4:
                spinnerCategoria.setSelection(3);
                break;
            case 5:
                spinnerCategoria.setSelection(4);
                break;
            case 6:
                spinnerCategoria.setSelection(5);
                break;
            case 7:
                spinnerCategoria.setSelection(6);
                break;
            case 8:
                spinnerCategoria.setSelection(7);
                break;
            case 9:
                spinnerCategoria.setSelection(8);
                break;
            case 10:
                spinnerCategoria.setSelection(9);
                break;
            case 11:
                spinnerCategoria.setSelection(10);
                break;
            case 12:
                spinnerCategoria.setSelection(11);
                break;
            case 13:
                spinnerCategoria.setSelection(12);
                break;
            case 14:
                spinnerCategoria.setSelection(13);
                break;
            case 15:
                spinnerCategoria.setSelection(14);
                break;
            case 16:
                spinnerCategoria.setSelection(15);
                break;
            case 17:
                spinnerCategoria.setSelection(16);
                break;
            case 18:
                spinnerCategoria.setSelection(17);
                break;
            case 19:
                spinnerCategoria.setSelection(18);
                break;
            case 20:
                spinnerCategoria.setSelection(19);
                break;
            case 21:
                spinnerCategoria.setSelection(20);
                break;
        }
        switch (producto.getIdInventario()){
            case 1:
                spinnerInventario.setSelection(2);
                break;
            case 2:
                spinnerInventario.setSelection(0);
                break;
            case 3:
                spinnerInventario.setSelection(1);
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
                if (!editTextNombre.getText().toString().equalsIgnoreCase("") && !editTextPrecio.getText().toString().equalsIgnoreCase("")
                        && !editTextPrecio.getText().toString().equalsIgnoreCase("0") && !editTextCantidad.getText().toString().equalsIgnoreCase("")
                        && !editTextCantidad.getText().toString().equalsIgnoreCase("0") && !editTextCaducidad.getText().toString().equalsIgnoreCase("")) {
                        producto.setNombre(String.valueOf(editTextNombre.getText()));
                        producto.setPrecio(Double.parseDouble(editTextPrecio.getText().toString()));
                        producto.setCantidad(Integer.parseInt(editTextCantidad.getText().toString()));
                        producto.setCaducidad(String.valueOf(editTextCaducidad.getText()));
                        switch (spinnerCategoria.getSelectedItemPosition()) {
                            case 0:
                                producto.setIdCategoria(1);
                                break;
                            case 1:
                                producto.setIdCategoria(2);
                                break;
                            case 2:
                                producto.setIdCategoria(3);
                                break;
                            case 3:
                                producto.setIdCategoria(4);
                                break;
                            case 4:
                                producto.setIdCategoria(5);
                                break;
                            case 5:
                                producto.setIdCategoria(6);
                                break;
                            case 6:
                                producto.setIdCategoria(7);
                                break;
                            case 7:
                                producto.setIdCategoria(8);
                                break;
                            case 8:
                                producto.setIdCategoria(9);
                                break;
                            case 9:
                                producto.setIdCategoria(10);
                                break;
                            case 10:
                                producto.setIdCategoria(11);
                                break;
                            case 11:
                                producto.setIdCategoria(12);
                                break;
                            case 12:
                                producto.setIdCategoria(13);
                                break;
                            case 13:
                                producto.setIdCategoria(14);
                                break;
                            case 14:
                                producto.setIdCategoria(15);
                                break;
                            case 15:
                                producto.setIdCategoria(16);
                                break;
                            case 16:
                                producto.setIdCategoria(17);
                                break;
                            case 17:
                                producto.setIdCategoria(18);
                                break;
                            case 18:
                                producto.setIdCategoria(19);
                                break;
                            case 19:
                                producto.setIdCategoria(20);
                                break;
                            case 20:
                                producto.setIdCategoria(21);
                                break;
                        }
                        switch (spinnerInventario.getSelectedItemPosition()) {
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
                        Toast.makeText(getActivity().getApplicationContext(),"Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
                        devolverAFragmentAnterior();
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

    private void devolverAFragmentAnterior() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (anteriorFragment.toLowerCase()) {
            case "nevera":
                ft.replace(R.id.frame, new Nevera()).addToBackStack(null);
                break;
            case "congelador":
                ft.replace(R.id.frame, new Congelador()).addToBackStack(null);
                break;
            case "despensa":
                ft.replace(R.id.frame, new Despensa()).addToBackStack(null);
                break;
            case "productos":
                ft.replace(R.id.frame, new Productos()).addToBackStack(null);
                break;
        }
        ft.commit();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this,
                Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
        editTextCaducidad.setText(fecha);
    }
}
