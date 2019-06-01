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
public class Inventarios extends Fragment {


    public Inventarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_inventario, container, false);
        getActivity().setTitle("Inventarios");
        Button btnNevera = (Button) vista.findViewById(R.id.buttonNevera);
        btnNevera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().addToBackStack(null);
                ft.replace(R.id.frame, new Nevera()).addToBackStack(null);
                ft.commit();
            }
        });

        Button btnCongelador = (Button) vista.findViewById(R.id.buttonCongelador);
        btnCongelador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().addToBackStack(null);
                ft.replace(R.id.frame, new Congelador()).addToBackStack(null);
                ft.commit();
            }
        });

        Button btnDespensa = (Button) vista.findViewById(R.id.buttonDespensa);
        btnDespensa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction().addToBackStack(null);
                ft.replace(R.id.frame, new Despensa()).addToBackStack(null);
                ft.commit();
            }
        });


        return vista;
    }

}
