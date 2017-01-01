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

public class EmentaMenuListItemAdapter extends BaseAdapter {

    private Context context;
    private List<Ementa> ementas;
    private LayoutInflater inflater;

    public EmentaMenuListItemAdapter(Context context, List<Ementa> ementas) {
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
            reusedView = inflater.inflate(R.layout.item_ementa_menu, null);
        }

        Ementa current = ementas.get(position);

        String temp;
        temp = current.getDiadasemana();
        ((TextView) reusedView.findViewById(R.id.textViewDiaMenu)).setText(temp);

        temp = current.getData();
        ((TextView) reusedView.findViewById(R.id.textViewDataMenu)).setText(temp);

        temp = current.getRefeicao();
        ((TextView) reusedView.findViewById(R.id.textViewRefeicaoMenu)).setText(temp);

        temp = current.getSopa();
        ((TextView) reusedView.findViewById(R.id.textViewSopaMenu)).setText(temp);

        temp = current.getCarne();
        ((TextView) reusedView.findViewById(R.id.textViewCarneMenu)).setText(temp);

        temp = current.getPeixe();
        ((TextView) reusedView.findViewById(R.id.textViewPeixeMenu)).setText(temp);

        temp = current.getVegetariano();
        ((TextView) reusedView.findViewById(R.id.textViewVegetarianoMenu)).setText(temp);

        temp = current.getSobremesa();
        ((TextView) reusedView.findViewById(R.id.textViewSobremesaMenu)).setText(temp);


        return reusedView;
    }
}
