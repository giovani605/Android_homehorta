package com.example.giovaniboss.homehorta;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.R.color.holo_blue_bright;

/**
 * Created by giovaniboss on 17/06/17.
 */
// A ideia dessa classe eh facilidatar o link entre o botao da interface e a planta

public class PlantaBotao extends LinearLayout implements View.OnClickListener { // poderia fazer de froma mais legal ainda
    Planta planta;
    Button botao;
    TextView texto;
    Home tela;

    public PlantaBotao(Context context, Planta p,Home tela,int width) {
        super(context);
        planta = p;
        botao =  new Button(context);
        botao.setText("info");
        texto = new TextView(context);
        this.texto.setText(p.getNome());
        Log.d("MEU SANTO",p.getNome());

        this.addView(texto);
        this.addView(botao);
        this.setOrientation(LinearLayout.HORIZONTAL);
       // this.setWeightSum(3);
       // botao.getLayoutParams().height = 1;
        texto.setWidth((width*2)/3);
        botao.setWidth(width/3);
        botao.setBackgroundColor(getResources().getColor(R.color.colorBotao));
       // texto.getLayoutParams().height = 2;
        this.botao.setOnClickListener(this);
        this.tela = tela;
    }

    @Override
    public void onClick(View view) {
        tela.criarTela(this);
    }
}
