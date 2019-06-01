package mycompra.app.controlador;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mycompra.app.R;
import mycompra.app.TextScanner;

/**
 * A simple {@link Fragment} subclass.
 */
public class Escanear extends Fragment {


    public Escanear() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_escanear, container, false);

        Button btnMercadona = (Button) vista.findViewById(R.id.btnMercadona);
        Button btnConsum = (Button) vista.findViewById(R.id.btnConsum);
        Button btnOtros = (Button) vista.findViewById(R.id.btnOtros);

        btnMercadona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getActivity().getBaseContext(), TextScanner.class);
                myintent.putExtra("supermercado", 1);
                startActivity(myintent);
            }
        });

        btnConsum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getActivity().getBaseContext(), TextScanner.class);
                myintent.putExtra("supermercado", 2);
                startActivity(myintent);
            }
        });

        btnOtros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getActivity().getBaseContext(), TextScanner.class);
                myintent.putExtra("supermercado", 3);
                startActivity(myintent);
            }
        });

        // Inflate the layout for this fragment
        return vista;
    }

}
