package com.example.giovaniboss.homehorta.banco_de_dados;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.giovaniboss.homehorta.Guia;
import com.example.giovaniboss.homehorta.Planta;

import java.util.ArrayList;


/**
 * Created by giovaniboss on 24/06/17.
 */

public class Banco extends SQLiteOpenHelper{
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;




    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    public Banco(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void criarTable(){
        String comando = "CREATE TABLE CARALHA ( ID_CARALHA INTEGER PRIMARY_KEY, ID_NDA INTEGER)";
        SQLiteDatabase db =  this.getReadableDatabase();
        db.execSQL(comando);
    }
    public void adicionarTable(){
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("ID_CARALHA", "0");
        values.put("ID_NDA", "20");

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("CARALHA", null, values);

    }
    public void consultarTable(){
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                "ID_CARALHA",
                "ID_NDA"};

        String[] selectionArgs = { "0" ,"100"};

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        Cursor c = db.rawQuery("SELECT * FROM CARALHA",null);
        /*
        Cursor c = db.query(
                "CARALHA",                     // The table to query
                projection,                               // The columns to return
                "ID_CARALHA ",                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,
                null// don't filter by row group// The sort order
        );*/


        c.moveToFirst();

        int a = c.getInt(c.getColumnIndex("ID_NDA"));

        Log.d("TESTE",""+a);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // aqui é um teste para ver se o database ja existe
        if(sqLiteDatabase.getVersion() == 10)
            return;
       // criarBancoPadrao();
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        sqLiteDatabase.setVersion(10);
    }

    public void criarBancoPadrao(){
        if(getReadableDatabase().getVersion() == 10)
            return;
        SQLiteDatabase db =  this.getReadableDatabase();

        SQLiteDatabase db2 =  this.getWritableDatabase();
        String cmd = "DROP TABLE IF EXISTS  Usuario";
        db2.execSQL(cmd);
        cmd = "DROP TABLE IF EXISTS Planta";
        db2.execSQL(cmd);
        cmd = "DROP TABLE IF EXISTS Guia";
        db2.execSQL(cmd);
        cmd = "DROP TABLE IF EXISTS tag";
        db2.execSQL(cmd);




        String comando;
        comando  = "CREATE TABLE IF NOT EXISTS Usuario (`idUsuario` VARCHAR(20) NOT NULL,`senha` VARCHAR(45) NULL,`email` VARCHAR(45) NULL, PRIMARY KEY (`idUsuario`))";
        db.execSQL(comando);
        comando = "CREATE TABLE IF NOT EXISTS Planta (\n" +
                "  `nome` VARCHAR(45) NOT NULL,\n" +
                "  `texto` MEDIUMTEXT NULL,\n" +
                "  `hora` TIME(6) NULL,\n" +
                "  `dias_para_colheita` INT NULL,\n" +
                "  PRIMARY KEY (`nome`))";
        db.execSQL(comando);
       // `mydb`.
        comando = "CREATE TABLE IF NOT EXISTS Guia (\n" +
                "  `idGuia` INT NOT NULL,\n" +
                "  `titulo` VARCHAR(30),\n" +
                "  `Texto` MEDIUMTEXT NULL,\n" +
                "  `Usuario_idUsuario` VARCHAR(20) NOT NULL,\n" +
                "  `Planta_nome` VARCHAR(45) NOT NULL,\n" +
                "  PRIMARY KEY (`idGuia`),\n" +
                "  CONSTRAINT `fk_Guia_Usuario1`\n" +
                "    FOREIGN KEY (`Usuario_idUsuario`)\n" +
                "    REFERENCES `Usuario` (`idUsuario`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `fk_Guia_Planta1`\n" +
                "    FOREIGN KEY (`Planta_nome`)\n" +
                "    REFERENCES `Planta` (`nome`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)";

        db.execSQL(comando);

        comando = "CREATE TABLE IF NOT EXISTS tag (\n" +
                "  `tag` VARCHAR(20) NOT NULL,\n" +
                "  `Guia_idGuia` INT NOT NULL,\n" +
                "  PRIMARY KEY (`Guia_idGuia`, `tag`),\n" +
                "  CONSTRAINT `fk_tag_Guia`\n" +
                "    FOREIGN KEY (`Guia_idGuia`)\n" +
                "    REFERENCES `Guia` (`idGuia`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION)";
        db.execSQL(comando);


        addPlantasPadrao();
        addUsuarioPadrao();
        addGuiasPadrao();
        addTagPadrao();
        Log.d("PADRAO","CRIEI OS PADROES");
        Log.d("TESTE", "criei tudo");
    }
    public void addTagPadrao(){
        SQLiteDatabase db2 =  this.getWritableDatabase();
        String comando;

        comando = "INSERT INTO tag (`tag`,`Guia_idGuia`) " +
                "Values('batata',2)";
        db2.execSQL(comando);
        comando = "INSERT INTO tag (`tag`,`Guia_idGuia`) " +
                "Values('batata',3)";
        db2.execSQL(comando);

    }
    public void addGuiasPadrao(){
        SQLiteDatabase db2 =  this.getWritableDatabase();
        String comando;
        comando = "INSERT INTO GUIA (`idGuia`,'titulo',`Texto`,`Usuario_idUsuario`,`Planta_nome`) "+
                " Values(2,'Batata 101','primeiro faça um buraco na terra, em seguida jogue a semente de batata lá','Giovani','batata')";
        db2.execSQL(comando);

        comando = "INSERT INTO GUIA (`idGuia`,'titulo',`Texto`,`Usuario_idUsuario`,`Planta_nome`) "+
                " Values(3,'How to Batata','Ta virando crime planta batata','Matheus','batata')";
        db2.execSQL(comando);
    }
    public void addUsuarioPadrao(){
        SQLiteDatabase db2 =  this.getWritableDatabase();

        String comando;
        comando = "INSERT INTO USUARIO (`idUsuario`,`senha`,`email`)" +
                " Values('Daniel','naoimporta','jao@hotmail.com')";
        db2.execSQL(comando);
        comando = "INSERT INTO USUARIO (`idUsuario`,`senha`,`email`)" +
                " Values('Matheus','naoimporta','jao@hotmail.com')";
        db2.execSQL(comando);
        comando = "INSERT INTO USUARIO (`idUsuario`,`senha`,`email`)" +
                " Values('Giovani','naoimporta','jao@hotmail.com')";
        db2.execSQL(comando);
        comando = "INSERT INTO USUARIO (`idUsuario`,`senha`,`email`)" +
                " Values('Juarez','naoimporta','jao@hotmail.com')";
        db2.execSQL(comando);
    }
    public void addPlantasPadrao(){

        SQLiteDatabase db2 =  this.getWritableDatabase();
        String comando;

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('batata','A batata, também conhecida como batata-inglesa" +
                " apesar de sua origem ser as regiões andinas do Peru e da Bolívia," +
                " é um dos principais alimentos de origem vegetal cultivados no mundo," +
                " sendo apenas menos cultivada que a cana-de-açúcar, o milho, o trigo e o arroz.','2222',60)";

        db2.execSQL(comando);


        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('alface','Um dos vegetais mais fáceis de cultivar, as sementes de alface vão crescer bem em quase qualquer lugar, então apenas polvilhe-as no vaso e dê um pouco de água." +
                " Além disso, você pode semear um monte de espécies diferentes no mesmo recipiente. " +
                "Importante: A alface precisa de muita luz solar, " +
                "leve isso em consideração ao escolher onde colocar o vaso.','2222',60)";

        db2.execSQL(comando);
        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('espinafre','Como a alface, o espinafre é fácil de plantar em um vaso." +
                " Basta jogar as sementes e dar um pouco de água. " +
                "A melhor parte é que você pode simplesmente cortar a planta, e ela vai crescer mais ainda."+
                " Importante: O espinafre gosta de uma boa quantidade de luz solar, bem como uma drenagem correta.','2222',40)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('acelga','Fácil de cultivar, basta plantar as sementes e dar água. Quando estiver pronto, corte as folhas e deixe a acelga crescer novamente.','2222',60)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('vagem','A vagem é uma das coisas mais fáceis de cultivar, de modo que você pode fazer disso um projeto para os seus filhos." +
                " Se você a colher regularmente, ela vai continuar produzindo mais e mais." +
                " Importante: A vagem, como todo feijão, é uma trepadeira, " +
                "portanto vai precisar de uma treliça onde possa se encostar.','2222',90)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('cebolinha','Incrivelmente fácil de plantar e pode produzir lindas flores. " +
                "A única desvantagem é que pode levar vários meses para que esteja pronta para a colheita.','2222',40)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('tomate','Tomates ficam muito bem em vasos e, se você quer resultados mais rápidos, plante o tomate-cereja." +
                " Esta também é uma planta trepadeira, portanto coloque uma treliça." +
                "  Importante: É mais difícil semear o tomate em um vaso," +
                 " portanto é melhor comprar mudas, em vez de sementes.','2222',60)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('pepino,''pepinos têm crescimento rápido, e um pepino fresco é um delicioso complemento para saladas." +
                " Também é ótimo para adicionar um aroma leve à água."+
                "Importante: Os pepinos também crescem para cima," +
                " então use uma treliça resistente no vaso','eh muito legal','2222',45)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('cenoura','Cenouras amam vasos fundos, mas podem levar até três meses para amadurecer.','2222',75)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('rabanete','Rabanetes têm crescimento rápido," +
                " e demoram apenas 25 dias para estarem prontos para o consumo." +
                " Após as sementes começarem a brotar, pode-os para que não compitam uns com os outros." +
                 " Importante: Rabanetes não gostam de calor, por isso, se você vive em uma área quente," +
                " procure espécies que são mais resistentes ao calor.','2222',70)";
        db2.execSQL(comando);

        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('pimentão','As espécies de pimenta que são adequadas para o cultivo em vaso são pimentões " +
                "e dedo-de-moça. Eles exigem certas condições para começar a crescer," +
                " por isso é melhor começar com mudas." +
                "Importante: Se você decidir plantar pimentas ou pimentões, " +
                "pode levar até três meses para que amadureçam. Se você está com pressa," +
                " então plante o mini pimentão vermelho - que só leva dois meses.','2222',100)";

        db2.execSQL(comando);


    }

    // Basicamnete cada clase tenq ter um desses
    public void criarXXX(){
        String comando = "CREATE TABLE CARALHA ( ID_CARALHA INTEGER PRIMARY_KEY, ID_NDA INTEGER)";
        SQLiteDatabase db =  this.getReadableDatabase();
        db.execSQL(comando);
    }
    public void inserirXXX(){
        SQLiteDatabase db = this.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put("ID_CARALHA", "0");
        values.put("ID_NDA", "20");

// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert("CARALHA", null, values);



    }
    public void selecionarXXX(){

        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                "ID_CARALHA",
                "ID_NDA"};

        String[] selectionArgs = { "0" ,"100"};

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        Cursor c = db.rawQuery("SELECT * FROM CARALHA",null);

        c.moveToFirst();

        int a = c.getInt(c.getColumnIndex("ID_NDA"));

        Log.d("TESTE",""+a);

    }

    public ArrayList<Guia> procurarGuias(String procurar) {
        SQLiteDatabase db = this.getReadableDatabase();


        int a = 0;
        Cursor c = db.rawQuery("SELECT * FROM GUIA INNER JOIN TAG ON GUIA.idGuia = tag.Guia_idGuia WHERE tag.tag = " + "'" + procurar + "'",null);
        ArrayList<Guia> lista = new ArrayList<Guia>();
        c.moveToFirst();
        Log.d("TESTE","Apertou o botao");
        do{

            String texto = c.getString(c.getColumnIndex("GUIA.Texto"));
            String texto2 = c.getString(c.getColumnIndex("GUIA.titulo"));
            String texto3 = c.getString(c.getColumnIndex("GUIA.Usuario_idUsuario"));
            Guia g = new Guia(texto,texto2,texto3);
            Log.d("TESTE","achei alguns guias"+a);
            a++;
            if(c.getCount() != 1)
                c.moveToNext();

            lista.add(g);
        }while(!c.isLast());

        return lista;



    }

    public Planta ProcuraPlanta(String procurar) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM PLANTA  WHERE nome = " + "'" + procurar + "'",null);

        c.moveToFirst();

        String texto = c.getString(c.getColumnIndex("nome"));
        int dias = c.getInt(c.getColumnIndex("dias_para_colheita"));
        Planta p = new Planta(texto,dias);
        String texto_desc = c.getString(c.getColumnIndex("texto"));
        p.setTexto(texto_desc);

        Log.d("TESTE","achei a planta");

        return p;



    }
}
/* comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('tomate','muito boa','2222',45)";
        db2.execSQL(comando);
        comando = "INSERT INTO Planta (\n" +
                "  `nome`,\n" +
                "  `texto` ,\n" +
                "  `hora` ,\n" +
                "  `dias_para_colheita`) \n" +
                " Values('batata2','eh muito legal','2222',45)";
        db2.execSQL(comando);
        comando = "INSERT INTO USUARIO (`idUsuario`,`senha`,`email`)" +
                " Values('joao333','naoimporta','jao@hotmail.com')";
        db2.execSQL(comando);
       */