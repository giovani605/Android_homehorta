package com.example.giovaniboss.homehorta;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.giovaniboss.homehorta.banco_de_dados.Banco;

import java.io.Serializable;
import java.util.ArrayList;

public class Home extends AppCompatActivity implements Serializable{
    public Lista_Plantas lista_plantas;
    public Pacote p ;
    int largura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lista_plantas = Lista_Plantas.getInstance();
        // criando umas plantas
        /*
        Planta p1 = new Planta("alface");
        Planta p2 = new Planta("tomate");

        lista_plantas.add_planta(p1);
        lista_plantas.add_planta(p2);



        Log.d("test", "" + x);
        PlantaBotao b1 = new PlantaBotao(this, p1, this, x);
       // PlantaBotao b2 = new PlantaBotao(this, p2, this, x);

        LinearLayout container_lista = (LinearLayout) findViewById(R.id.gride);

        container_lista.addView(b1);
       // container_lista.addView(b2);
        addNovaPlanta(p2);*/


        DisplayMetrics metrics = new DisplayMetrics();

        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int x = metrics.widthPixels;
        largura = x;
        notificar();


        Banco b= new Banco(this);

        b.criarBancoPadrao();
       // b.addGuiasPadrao();
     //   b.addTagPadrao();
       // p = new Pacote(this);
    }

    public void notificar() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.icone)
                        .setContentTitle("HomeHorta")
                        .setContentText("Regue suas plantas às 8:00 e às 18:00");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, Home.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(Home.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        int mId = 999;
        mNotificationManager.notify(mId, mBuilder.build());


    }


    public void criarTela(PlantaBotao plantaBotao) {
        Intent intent = new Intent(this, planta_info.class);
        intent.putExtra("plantinha", plantaBotao.planta);
        startActivity(intent);

    }


    public void addNovaPlanta(Planta planta) {
       // lista_plantas.add(planta);
        DisplayMetrics metrics = new DisplayMetrics();



        PlantaBotao _botao = new PlantaBotao(this, planta, this, largura);
        LinearLayout container_lista = (LinearLayout) findViewById(R.id.gride);
       // container_lista.setWeightSum(5);
        container_lista.addView(_botao);
      //  _botao.getLayoutParams().height = 1;
    }
    /*
    public void confirma_planta(View view) {

        Planta planta = new Planta("batata");
        lista_plantas.add(planta);
        DisplayMetrics metrics = new DisplayMetrics();



        PlantaBotao _botao = new PlantaBotao(this, planta, this, largura);
        LinearLayout container_lista = (LinearLayout) findViewById(R.id.gride);
        container_lista.addView(_botao);


    }*/

    @Override
    protected void onResume(){
        super.onResume();

        if(lista_plantas.estado == 1){
            Log.d("tesao","cheguei aqui");
           this.addNovaPlanta(lista_plantas.ultima);
            lista_plantas.estado = 0;
        }

    }
    public void add_planta_tela(View view) {
        Intent intent = new Intent(this, cadastrar_horta.class);

        intent.putExtra("lista",this.lista_plantas);
        startActivity(intent);
    }
    public void crair_tela_buscar(View view) {
        Intent intent = new Intent(this, BuscarGuias.class);

        //intent.putExtra("lista",this.lista_plantas);

        startActivity(intent);
    }
}