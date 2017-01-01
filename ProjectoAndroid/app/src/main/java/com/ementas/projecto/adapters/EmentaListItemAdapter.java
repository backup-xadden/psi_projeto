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

        String temp;
        temp = "Dia da Semana: " + current.getDiadasemana();
        ((TextView) reusedView.findViewById(R.id.textViewDiaDaSemana)).setText(temp);

        temp = "Data: " + current.getData();
        ((TextView) reusedView.findViewById(R.id.textViewDataEmenta)).setText(temp);

        temp = "Refeição: " + current.getRefeicao();
        ((TextView) reusedView.findViewById(R.id.textViewRefeicaoEmenta)).setText(temp);

        temp = "Sopa: " + current.getSopa();
        ((TextView) reusedView.findViewById(R.id.textViewSopa)).setText(temp);

        temp = "Carne: " + current.getCarne();
        ((TextView) reusedView.findViewById(R.id.textViewCarne)).setText(temp);

        temp = "Peixe: " + current.getPeixe();
        ((TextView) reusedView.findViewById(R.id.textViewPeixe)).setText(temp);

        temp = "Vegetariano: " + current.getVegetariano();
        ((TextView) reusedView.findViewById(R.id.textViewVegetariano)).setText(temp);

        temp = "Sobremesa: " + current.getSobremesa();
        ((TextView) reusedView.findViewById(R.id.textViewSobremesa)).setText(temp);


        return reusedView;
    }
}
