package com.example.mynotes.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.mynotes.R;
import com.example.mynotes.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;

public class FragmentNota extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private List<NotaEntity> notaEntityList;
    private MyNotaRecyclerViewAdapter adapter;
    private DialogFragmentNuevaNotaViewModel notaViewModel;

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
        setHasOptionsMenu(true);
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

            adapter = new MyNotaRecyclerViewAdapter(notaEntityList, getActivity());
            recyclerView.setAdapter(adapter);

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        notaViewModel = ViewModelProviders.of(getActivity()).get(DialogFragmentNuevaNotaViewModel.class);
        notaViewModel.getAllNotas().observe(getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(List<NotaEntity> notaEntities) {
                adapter.setNewNotas(notaEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu_nota_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_note:
                mostrarDialogoNuevaNota();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarDialogoNuevaNota() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        DialogFragmentNuevaNota dialogNuevaNota = new DialogFragmentNuevaNota();
        dialogNuevaNota.show(fm, "DialogFragmentNuevaNota");
    }
}
