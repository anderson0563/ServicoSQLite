package br.anderson.acessosistemaservico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button validation = (Button)findViewById(R.id.button);

        this.criarBancoAutenticacao();

        validation.setOnClickListener(v -> {
            Intent nextActivity = new Intent(this, TelaPrincipal.class);
            EditText login = (EditText) findViewById(R.id.editLogin);
            EditText password = (EditText) findViewById(R.id.editPassword);

            nextActivity.putExtra("login", login.getText().toString());
            nextActivity.putExtra("password", password.getText().toString());

            startActivity(nextActivity);
        });
    }

    public void criarBancoAutenticacao(){
        SQLiteDatabase myDB = openOrCreateDatabase("user.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (login VARCHAR(20), password VARCHAR(20))");
        ContentValues registro = new ContentValues();
        registro.put("login", "admin");
        registro.put("password", "12345678");

        myDB.insert("user", null, registro);

        myDB.close();
    }
}