package com.example.giovaniboss.homehorta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class planta_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planta_info);
        // mostra o nome da planta e as informacoes
        Intent intent = getIntent();
        Planta p  = (Planta) intent.getSerializableExtra("plantinha");

        TextView t = (TextView) findViewById(R.id.texto_planta_nome);
        t.setText(p.getNome());

        TextView t2 = (TextView) findViewById(R.id.texto_planta_descricao);
        t2.setText(p.texto);

        TextView t3 = (TextView) findViewById(R.id.texto_planta_dias);
        t3.setText("Dias para a colheita: " + p.tempo_colheita);

    }
}
