package mycompra.app.controlador;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mycompra.app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NuevaLista extends Fragment {


    public NuevaLista() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_nueva_lista, container, false);

        Button btnAceptar = (Button) vista.findViewById(R.id.buttonAceptarNuevaLista);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame,new Listas()).addToBackStack(null);
                ft.commit();
            }

        });

        Button btnCancelar = (Button) vista.findViewById(R.id.buttonCancelarNuevaLista);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frame,new Listas()).addToBackStack(null);
                ft.commit();
            }

        });

        return vista;
    }

}
