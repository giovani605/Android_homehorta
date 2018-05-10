package com.example.giovaniboss.homehorta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giovaniboss.homehorta.banco_de_dados.Banco;

import java.util.ArrayList;

public class BuscarGuias extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_guias);

        TextView t = (TextView) findViewById(R.id.entrada_Buscar_guias);
        t.setWidth(200);
    }
    public void chamar_busca(View view){
        // listar componentes existentes antes
        TextView t = (TextView) findViewById(R.id.entrada_Buscar_guias);
        Banco b = new Banco(this);

        String procurar = t.getText().toString();
        ArrayList<Guia> lista_encontrados = b.procurarGuias(procurar);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_BuscarGuias);

        DisplayMetrics metrics = new DisplayMetrics();

        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int x = metrics.widthPixels;

        for(Guia g : lista_encontrados ){
            Log.d("TESTE",""+g.getTexto());
            GuiaBotao guiaBotao = new GuiaBotao(this,g,x,this);
            layout.addView(guiaBotao);
            // fazer um botao pra cada guia encontrado estilo plantaBotao
            // adicionar os onclick listener
        }


    }
    public void criarTela_Guia(GuiaBotao b) {
        Intent intent = new Intent(this, MostrarGuia.class);
        intent.putExtra("guia", b.guia);
        startActivity(intent);

    }
}
