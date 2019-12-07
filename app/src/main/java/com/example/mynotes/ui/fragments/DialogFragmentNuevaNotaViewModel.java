package com.example.mynotes.ui.fragments;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mynotes.NotaRepository;
import com.example.mynotes.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;

public class DialogFragmentNuevaNotaViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas;
    private NotaRepository repository;

    public DialogFragmentNuevaNotaViewModel(Application application){
        super(application);

        repository = new NotaRepository(application);
        allNotas = repository.getAll();
    }

    //Fragemento que necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas(){
        return allNotas;
    }

    //Fragemento que inserte una nueva nota debe avisarle a este viewmodel
    public void insertNota(NotaEntity nuevaNotaEntity){
        repository.insert(nuevaNotaEntity);
    }

    //Actualizar una nota creada
    public void updateNota(NotaEntity actualizarNotaEntity){
        repository.update(actualizarNotaEntity);
    }
}
