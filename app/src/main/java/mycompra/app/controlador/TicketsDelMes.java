package mycompra.app.controlador;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import mycompra.app.R;
import mycompra.app.dao.MesDAO;
import mycompra.app.dao.ProductoTicketDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketsDelMes extends Fragment {

    public TicketsDelMes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        return view;
    }

}
