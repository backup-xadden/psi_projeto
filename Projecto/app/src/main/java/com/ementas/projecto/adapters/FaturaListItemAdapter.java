package com.ementas.projecto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ementas.projecto.R;
import com.ementas.projecto.models.Fatura;

import java.util.List;


public class FaturaListItemAdapter extends BaseAdapter {

    private Context context;
    private List<Fatura> faturas;
    private LayoutInflater inflater;

    public FaturaListItemAdapter(Context context, List<Fatura> faturas) {
        this.context = context;
        this.faturas = faturas;
    }

    @Override
    public int getCount() {
        return (faturas != null ? faturas.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return faturas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return faturas.get(position).getId();
    }

    @Override
    public View getView(int position, View reusedView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (reusedView == null) {
            reusedView = inflater.inflate(R.layout.item_fatura, null);
        }

        Fatura current = faturas.get(position);

        String temp;
        temp = "Data: " + current.getData();
        ((TextView) reusedView.findViewById(R.id.textViewData)).setText(temp);

        temp = "Refeicao: " + current.getRefeicao();
        ((TextView) reusedView.findViewById(R.id.textViewRefeicao)).setText(temp);

        temp = "Prato: " + current.getPrato();
        ((TextView) reusedView.findViewById(R.id.textViewPrato)).setText(temp);

        temp = "Preco: " + current.getPreco() + "€";
        ((TextView) reusedView.findViewById(R.id.textViewPreco)).setText(temp);

        temp = "Cantina #" + current.getCantina();
        ((TextView) reusedView.findViewById(R.id.textViewCantina)).setText(temp);




        return reusedView;
    }
}
