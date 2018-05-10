package com.example.giovaniboss.homehorta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarGuia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_guia);



        // ler o guia vindo da intent
        Intent intent = getIntent();
       Guia g  = (Guia) intent.getSerializableExtra("guia");


        // adicionar os elementos na tela
        TextView t1 = (TextView) findViewById(R.id.texto_autor);
        t1.setText(g.titulo + " de " + g.autor);

        TextView t2 = (TextView) findViewById(R.id.texto_guia);
        t2.setText(g.getTexto());


    }
}
