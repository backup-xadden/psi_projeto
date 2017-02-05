package com.ementas.projecto.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ementas.projecto.R;
import com.ementas.projecto.adapters.EmentaListItemAdapter;
import com.ementas.projecto.db.LocalCache;
import com.ementas.projecto.models.Ementa;

import java.util.List;

public class EmentaFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private LocalCache db;
    private List<Ementa> ementas;

    private int mPage;

    public static EmentaFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        EmentaFragment fragment = new EmentaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        db = new LocalCache(getActivity());
        ementas = db.findWeeklyEmenta(mPage);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fg_ementa, container, false);
        ListView lv_ementa = (ListView) view;
        lv_ementa.setAdapter(new EmentaListItemAdapter(getActivity(), ementas));
        return view;
    }
}
