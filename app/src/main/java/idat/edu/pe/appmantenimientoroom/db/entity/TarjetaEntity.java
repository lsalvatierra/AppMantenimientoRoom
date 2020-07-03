package idat.edu.pe.appmantenimientoroom.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tarjeta")
public class TarjetaEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String titulo;
    public String contenido;
    public boolean importante;
    public String color;



    public TarjetaEntity(int id, String titulo, String contenido, boolean importante, String color) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.importante = importante;
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

    public boolean isImportante() {
        return importante;
    }

    public void setImportante(boolean importante) {
        this.importante = importante;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
