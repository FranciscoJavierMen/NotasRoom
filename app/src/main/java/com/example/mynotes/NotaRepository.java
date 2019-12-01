package com.example.mynotes;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mynotes.db.NotaRoomDatabase;
import com.example.mynotes.db.dao.NotaDao;
import com.example.mynotes.db.entity.NotaEntity;

import java.util.ArrayList;

public class NotaRepository {
    private NotaDao notaDao;
    private LiveData<ArrayList<NotaEntity>> allNotas;
    private LiveData<ArrayList<NotaEntity>> allNotasFavoritas;

    public NotaRepository(Application application){
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getFavoritas();
    }

    public LiveData<ArrayList<NotaEntity>> getAll(){
       return allNotas;
    }

    public LiveData<ArrayList<NotaEntity>> getAllFavoritas(){
        return allNotasFavoritas;
    }

    public void insert(NotaEntity nota){
        new inserAsyncTask(notaDao).execute(nota);
    }

    private static class inserAsyncTask extends AsyncTask<NotaEntity, Void, Void>{
        private NotaDao notaDaoAsyncTask;

        inserAsyncTask(NotaDao dao){
            notaDaoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsyncTask.insert(notaEntities[0]);
            return null;
        }
    }
}
