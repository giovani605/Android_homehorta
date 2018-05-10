package com.example.giovaniboss.homehorta;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by giovaniboss on 20/06/17.
 */

public class Lista_Plantas implements Serializable{
    private static Lista_Plantas instance;

    public ArrayList<Planta> lista_plantas;
    public int estado = 0;
    Planta ultima;
    private Lista_Plantas(){
        lista_plantas = new ArrayList<Planta>();
    }
    public static Lista_Plantas getInstance(){
        if(instance == null)
            instance = new Lista_Plantas();
        return instance;

    }
    public void add_planta(Planta p ){
        lista_plantas.add(p);
        ultima = p;
    }
}
