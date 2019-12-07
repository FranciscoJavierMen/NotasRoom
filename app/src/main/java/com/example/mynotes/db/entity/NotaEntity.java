package com.example.mynotes.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notas")
public class NotaEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String titulo;
    public String contenido;
    public boolean favorito;
    public String color;

    public NotaEntity(String titulo, String contenido, boolean favorito, String color) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.favorito = favorito;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
