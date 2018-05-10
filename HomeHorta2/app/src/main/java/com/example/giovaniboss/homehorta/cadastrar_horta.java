package com.example.giovaniboss.homehorta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giovaniboss.homehorta.banco_de_dados.Banco;

import java.util.ArrayList;

public class cadastrar_horta extends AppCompatActivity  {

    Home tela;
    Button botao;
    EditText texto_nome;
    Lista_Plantas p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_horta);

        LinearLayout lay = (LinearLayout) findViewById(R.id.layout_criar_horta);

    /*
        LinearLayout lay1 = new LinearLayout(this);
        texto_nome = new EditText(this);
        texto_nome.setWidth(40);
        TextView _nome = new TextView(this);
        _nome.setText("nome: ");
        lay1.addView(_nome);
        lay1.addView(texto_nome);*/

        // Adicionar o resto
        //LinearLayout lay2 = new LinearLayout(this);
        //EditText texto_dias = new EditText(this);
    /*
        botao = new Button(this);
        botao.setText("Confirmar");
        botao.setOnClickListener(this);*/

       // lay.addView(lay1);
       // lay.addView(botao);


        p = Lista_Plantas.getInstance();

    }
    public void setTela(Home t){
        tela = t;
    }

    public void confirmar(View view) {

        TextView entrada = (TextView) findViewById(R.id.entrada_cadastrar_horta);
        String procurar  = entrada.getText().toString();
        Banco b = new Banco(this);
        Planta planta = b.ProcuraPlanta(procurar);
        if(p == null){
            LinearLayout lay = (LinearLayout) findViewById(R.id.layout_criar_horta);
            TextView t = new TextView(this);
            t.setText("planta não encontrada");
            lay.addView(t);
            return;
        }

        p.add_planta(planta);
        p.estado = 1;
        LinearLayout lay = (LinearLayout) findViewById(R.id.layout_criar_horta);
        lay.removeAllViewsInLayout();
        TextView t = new TextView(this);
        t.setText("planta adicionada a horta");
        lay.addView(t);

        //TODO
        // procurar se ha no database essa planta e recuperar as informações
        //

    }


}
