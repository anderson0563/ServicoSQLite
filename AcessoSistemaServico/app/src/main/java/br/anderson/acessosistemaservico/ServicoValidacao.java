package br.anderson.acessosistemaservico;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class ServicoValidacao extends Service {
    private final IBinder mbidner = new MyBinder();

    public class MyBinder extends Binder {
        ServicoValidacao getService(){
            return ServicoValidacao.this;
        }
    }

    public IBinder onBind(Intent intent){
        return mbidner;
    }



    public ServicoValidacao() {
    }

    public boolean validar(String login, String password){

        SQLiteDatabase myDB = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (login VARCHAR(20), password VARCHAR(20))");


        Cursor myCursor = myDB.rawQuery("select login, password from user", null);

        myCursor.moveToNext();
        String loginString = myCursor.getString(0);
        String passwordString = myCursor.getString(1);
        myDB.close();
        return (loginString.equalsIgnoreCase(login) && passwordString.equalsIgnoreCase(password));
    }

}