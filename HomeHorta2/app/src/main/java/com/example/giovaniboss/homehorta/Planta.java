package com.example.giovaniboss.homehorta;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by giovaniboss on 17/06/17.
 */

public class Planta implements Serializable{
    String nome;
    Date dia_plantio;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    String texto;
    int tempo_cultivo;
    int tempo_colheita;

    public Planta(String texto, int dias) {
        nome = texto;
        tempo_colheita = dias;
    }

    public Date getDia_plantio() {
        return dia_plantio;
    }

    public void setDia_plantio(Date dia_plantio) {
        this.dia_plantio = dia_plantio;
    }



    public Planta(String n){
        this.nome = n;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



}
