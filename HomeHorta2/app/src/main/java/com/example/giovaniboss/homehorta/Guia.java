package com.example.giovaniboss.homehorta;

import java.io.Serializable;

/**
 * Created by giovaniboss on 25/06/17.
 */

public class Guia implements Serializable{
    public Guia(String texto, String tit, String author) {
        this.texto = texto;
        this.titulo = tit;
        this.autor = author;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    String texto;
    String autor;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    String titulo;

    public Guia(String texto,String a) {
        this.texto = texto;
        autor = a;
    }
}
