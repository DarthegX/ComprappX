package mycompra.app.controlador;


import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mycompra.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoMensual extends Fragment {


    public InfoMensual() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_info_mensual, container, false);

        return vista;
    }


}
