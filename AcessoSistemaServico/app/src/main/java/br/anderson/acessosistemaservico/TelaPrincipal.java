package br.anderson.acessosistemaservico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TelaPrincipal extends AppCompatActivity implements ServiceConnection {

    private ServicoValidacao validacao;
    Intent intent;
    String login, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        Intent lastActivity = getIntent();

        login = lastActivity.getStringExtra("login");
        password = lastActivity.getStringExtra("password");

    }

    protected void onResume(){
        super.onResume();
        Intent intent = new Intent(this, ServicoValidacao.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    protected void onPause(){
        super.onPause();
        unbindService(this);
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ServicoValidacao.MyBinder b = (ServicoValidacao.MyBinder) iBinder;
        validacao = b.getService();

        if(validacao.validar(login, password))
            Toast.makeText(this, "Access Granted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Access Denied", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        validacao=null;
    }
}