package com.example.giovaniboss.homehorta;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by giovaniboss on 26/06/17.
 */

public class GuiaBotao extends LinearLayout implements View.OnClickListener{
    Guia guia;
    Button botao;
    TextView texto;
    BuscarGuias tela;
    public GuiaBotao(Context context, Guia p, int width,BuscarGuias t) {
        super(context);
        guia = p;
        botao =  new Button(context);
        botao.setText("abrir");
        texto = new TextView(context);
        this.texto.setText(p.titulo);

        botao.setWidth(width/3);
        texto.setWidth((2*width)/3);
        this.addView(texto);
        this.addView(botao);
        botao.setBackgroundColor(getResources().getColor(R.color.colorBotao));
        tela = t;
        botao.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        tela.criarTela_Guia(this);
    }
}
