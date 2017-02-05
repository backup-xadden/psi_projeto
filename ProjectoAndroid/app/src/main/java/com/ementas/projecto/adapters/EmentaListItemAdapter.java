package com.ementas.projecto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ementas.projecto.R;
import com.ementas.projecto.models.Ementa;

import java.util.List;

public class EmentaListItemAdapter extends BaseAdapter {

    private Context context;
    private List<Ementa> ementas;
    private LayoutInflater inflater;

    public EmentaListItemAdapter(Context context, List<Ementa> ementas) {
        this.context = context;
        this.ementas = ementas;
    }

    @Override
    public int getCount() {
        return (ementas != null ? ementas.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return ementas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ementas.get(position).getId();
    }

    @Override
    public View getView(int position, View reusedView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (reusedView == null) {
            reusedView = inflater.inflate(R.layout.item_ementa, null);
        }

        Ementa current = ementas.get(position);

        ((TextView) reusedView.findViewById(R.id.textViewDiaDaSemanaValue)).setText(current.getDiadasemana());

        ((TextView) reusedView.findViewById(R.id.textViewDataEmentaValue)).setText(current.getData());

        ((TextView) reusedView.findViewById(R.id.textViewRefeicaoEmentaValue)).setText(current.getRefeicao());

        ((TextView) reusedView.findViewById(R.id.textViewSopaValue)).setText(current.getSopa());

        ((TextView) reusedView.findViewById(R.id.textViewCarneValue)).setText(current.getCarne());

        ((TextView) reusedView.findViewById(R.id.textViewPeixeValue)).setText(current.getPeixe());

        ((TextView) reusedView.findViewById(R.id.textViewVegetarianoValue)).setText(current.getVegetariano());

        ((TextView) reusedView.findViewById(R.id.textViewSobremesaValue)).setText(current.getSobremesa());


        return reusedView;
    }
}
