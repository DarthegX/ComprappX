package mycompra.app.controlador;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mycompra.app.R;
import mycompra.app.adaptersRecycler.AdapterTickets;
import mycompra.app.dao.MesDAO;
import mycompra.app.dao.SupermercadoDAO;
import mycompra.app.dao.TicketDAO;
import mycompra.app.modelo.Mes;
import mycompra.app.modelo.Supermercado;
import mycompra.app.modelo.Ticket;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketsDelMes extends Fragment {

    ArrayList<String> listSupermercados;
    ArrayList<String> listFechas;
    ArrayList<String> listPrecios;
    RecyclerView recyclerView;
    private MesDAO mesDAO;
    private TicketDAO ticketDAO;
    private SupermercadoDAO supermercadoDAO;
    private ArrayList<Mes> listaMes;
    private ArrayList<Ticket> listaTicketsMes;
    private ArrayList<Supermercado> listaSupermercados;
    private Mes mes;

    public TicketsDelMes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        getActivity().setTitle("Tickets del mes");
        recyclerView = view.findViewById(R.id.RecyclerTickets);

        llenarListaTickets();

        AdapterTickets adapter = new AdapterTickets(listSupermercados,listFechas,listPrecios);

        recyclerView.setAdapter(adapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton btnAnyadirTicket = view.findViewById(R.id.btnAnyadirTicket);
        btnAnyadirTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private void llenarListaTickets() {
        ticketDAO = new TicketDAO(getActivity().getApplicationContext());
        supermercadoDAO = new SupermercadoDAO(getActivity().getApplicationContext());
        mesDAO = new MesDAO(getActivity().getApplicationContext());

        listaMes = mesDAO.getMesList();
        listaSupermercados = supermercadoDAO.getSupermercadoList();

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/YYYY");
        String fechaActual = mdformat.format(date);

        String[] partesFecha = fechaActual.split("/");
        String mesString = partesFecha[1];
        String anyo = partesFecha[2];

        String nombreMes = "";

        switch (Integer.parseInt(mesString)) {
            case 1:
                nombreMes = "enero";
                break;
            case 2:
                nombreMes = "febrero";
                break;
            case 3:
                nombreMes = "marzo";
                break;
            case 4:
                nombreMes = "abril";
                break;
            case 5:
                nombreMes = "mayo";
                break;
            case 6:
                nombreMes = "junio";
                break;
            case 7:
                nombreMes = "julio";
                break;
            case 8:
                nombreMes = "agosto";
                break;
            case 9:
                nombreMes = "septiembre";
                break;
            case 10:
                nombreMes = "octubre";
                break;
            case 11:
                nombreMes = "noviembre";
                break;
            case 12:
                nombreMes = "diciembre";
                break;
        }

        for (int i = 0; i < listaMes.size(); i++) {
            if (Integer.parseInt(anyo) == listaMes.get(i).getAnyo() && listaMes.get(i).getNombre().equalsIgnoreCase(nombreMes)) {
                mes = listaMes.get(i);
                break;
            }
        }

        listaTicketsMes = ticketDAO.getTicketListByMes(mes.getId());

        listSupermercados = new ArrayList<String>();
        listFechas = new ArrayList<String>();
        listPrecios = new ArrayList<String>();

        for (int i = 0; i < listaTicketsMes.size(); i++) {
            listPrecios.add(String.valueOf(listaTicketsMes.get(i).getPrecio()));
            listFechas.add(listaTicketsMes.get(i).getFecha());
            listSupermercados.add(listaSupermercados.get(listaTicketsMes.get(i).getIdSupermercado() - 1).getNombre());
        }
    }
}
