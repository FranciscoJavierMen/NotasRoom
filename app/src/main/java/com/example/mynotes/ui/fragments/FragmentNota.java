package com.example.mynotes.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.mynotes.R;
import com.example.mynotes.db.entity.NotaEntity;

import java.util.ArrayList;

public class FragmentNota extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private ArrayList<NotaEntity> notaEntityList;
    private MyNotaRecyclerViewAdapter adapter;

    public FragmentNota() {
    }

    @SuppressWarnings("unused")
    public static FragmentNota newInstance(int columnCount) {
        FragmentNota fragment = new FragmentNota();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (view.getId() == R.id.listPortrait){
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
                int columnNumber = (int)(dpWidth / 180);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(columnNumber, StaggeredGridLayoutManager.VERTICAL));
            }

            notaEntityList = new ArrayList<>();
            notaEntityList.add(new NotaEntity("Compra del día", "Comprar pan tostado y mermelada", true, R.color.colorAccent));
            notaEntityList.add(new NotaEntity("Hacer ejercicio", "Recordar hacer ehercicio cuando los demás se vallan a comer por la despedida de los colombianos", false, R.color.colorPrimaryDark));
            notaEntityList.add(new NotaEntity("Comprar chamarra", "No olvidar pasar acomprar la chamarra de cuero", true, R.color.colorPrimary));

            adapter = new MyNotaRecyclerViewAdapter(notaEntityList, getActivity());
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

}
