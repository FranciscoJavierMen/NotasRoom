package com.example.mynotes.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mynotes.db.entity.NotaEntity;

import java.util.ArrayList;

@Dao
public interface NotaDao {
    //Obtener datos de latabla de notas
    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    LiveData<ArrayList<NotaEntity>> getAll();

    //Obtener la lista de notas favoritas
    @Query("SELECT * FROM notas WHERE favorito LIKE 'true'")
    LiveData<ArrayList<NotaEntity>> getFavoritas();

    //Insertar datos en la tabla de notas
    @Insert
    void insert(NotaEntity nota);

    //Actualizar datos de latabla de notas
    @Update
    void update(NotaEntity nota);

    //Eliminar todas las notas
    @Query("DELETE FROM notas")
    void deleteAll();

    //Eliminar una sola nota de la tabla de notas
    @Query("DELETE FROM notas WHERE id = :idNota")
    void deleteNote(int idNota);
}
