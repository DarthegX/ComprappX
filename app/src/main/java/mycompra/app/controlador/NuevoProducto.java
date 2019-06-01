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

import java.util.Calendar;

import mycompra.app.R;
import mycompra.app.dao.ProductoDAO;
import mycompra.app.modelo.Producto;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuevoProducto extends Fragment implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private ProductoDAO productoDAO;
    private String anteriorFragment;
    private EditText editTextNombre;
    private EditText editTextPrecio;
    private EditText editTextCantidad;
    private EditText editTextCaducidad;
    private TextView textViewIdInventario;
    private Spinner spinnerInventario;
    private Spinner spinnerCategoria;
    private ArrayAdapter<CharSequence> adapterInventario;
    private ArrayAdapter<CharSequence> adapterCategoria;
    private Button btnCancelar;
    private Button btnAnyadir;
    private String fecha;

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

        spinnerInventario = view.findViewById(R.id.spinnerInventarioNuevoProducto);
        textViewIdInventario = view.findViewById(R.id.textViewIdInventario);
        spinnerCategoria = view.findViewById(R.id.spinnerCategoriaNuevoProducto);
        editTextPrecio = view.findViewById(R.id.editTextPrecioNuevoProducto);
        editTextCantidad = view.findViewById(R.id.editTextCantidadNuevoProducto);
        editTextCaducidad = view.findViewById(R.id.editTextCaducidadNuevoProducto);
        btnAnyadir = view.findViewById(R.id.btnAnyadirFragmentNuevoProducto);
        editTextNombre = view.findViewById(R.id.editTextNombreNuevoProducto);

        if (anteriorFragment.equalsIgnoreCase("productos")) {
            adapterInventario = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                    R.array.inventarios, android.R.layout.simple_spinner_item);
            adapterInventario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerInventario.setAdapter(adapterInventario);
            spinnerInventario.setOnItemSelectedListener(this);
        } else {
            spinnerInventario.setVisibility(View.GONE);
            textViewIdInventario.setVisibility(View.GONE);
        }

        adapterCategoria = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.categorias, android.R.layout.simple_spinner_item);
        adapterCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerCategoria.setOnItemSelectedListener(this);

        btnCancelar = view.findViewById(R.id.cancelarNuevoProducto);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                }
                ft.commit();
            }
        });

        editTextCaducidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnAnyadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextNombre.getText().toString().equalsIgnoreCase("") && !editTextPrecio.getText().toString().equalsIgnoreCase("")
                && !editTextPrecio.getText().toString().equalsIgnoreCase("0") && !editTextCantidad.getText().toString().equalsIgnoreCase("")
                && !editTextCaducidad.getText().toString().equalsIgnoreCase("")) {
                    Producto producto = new Producto();
                    producto.setNombre(String.valueOf(editTextNombre.getText()));
                    producto.setPrecio(Double.parseDouble(editTextPrecio.getText().toString()));
                    producto.setCantidad(Integer.parseInt(editTextCantidad.getText().toString()));
                    producto.setCaducidad(String.valueOf(editTextCaducidad.getText()));
                    producto.setCaducidad(fecha);
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
                            break;
                    }
                    productoDAO.insert(producto);
                    editTextNombre.setText("");
                    editTextPrecio.setText("");
                    editTextCantidad.setText("");
                    editTextCaducidad.setText("");
                    Toast.makeText(getActivity().getApplicationContext(), "Producto añadido correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Rellena todos los campos correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), this,
                Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
        editTextCaducidad.setText(fecha);
    }
}
