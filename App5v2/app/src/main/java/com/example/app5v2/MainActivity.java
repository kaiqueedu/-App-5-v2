package com.example.app5v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText userEdit;
    private EditText senhaEdit;
    private Button logar;
    private CheckBox checkBox;
    private TextView newUser;

    String usuario = "";
    String senha = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i(getString(R.string.tag), "Classe: "+ getClass().getSimpleName() +
                "| Método: onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEdit  = findViewById(R.id.editText_user);
        senhaEdit = findViewById(R.id.editText_senha);
        logar     = findViewById(R.id.button_logar);
        checkBox  = findViewById(R.id.checkBox_lembrar);
        newUser   = findViewById(R.id.textView_newUser);
        logar.setOnClickListener(this);
        newUser.setOnClickListener(this);

        mSharedPreferences = this.getPreferences(MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

    }

    @Override
    protected void onStart() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onResume()");

        verificarPreferencias();
        super.onResume();
    }

     @Override
     protected void onPause() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onPause()");super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onStop()");super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(getString(R.string.tag), "Classe: " + getClass().getSimpleName() +
                "| Método : onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if (v == logar) {
            usuario = userEdit.getText().toString();
            senha   = senhaEdit.getText().toString();

            if( usuario.isEmpty() || senha.isEmpty() ){
                Toast.makeText(this,R.string.erro_entrada_msg, Toast.LENGTH_SHORT).show();
            }

            salvarPreferencias();
            abriBoasVindas();
            return;
        }
        if(v == newUser){
            Intent in = new Intent(this, NovoUsuarioActivity.class);
            startActivity(in);
            return;
        }
    }

    private void abriBoasVindas(){
        Intent intent = new Intent(this, BemVindoActivity.class );

        Bundle args = new Bundle();
        args.putString(getString(R.string.key_usuario), usuario);
        args.putString(getString(R.string.key_senha), senha);

        intent.putExtras(args);
        startActivity(intent);
    }

    private void salvarPreferencias(){

     if(checkBox.isChecked()){
         mEditor.putString(getString(R.string.key_usuario), usuario);
         mEditor.commit();mEditor.putString(getString(R.string.key_senha), senha);
         mEditor.commit();mEditor.putBoolean(getString(R.string.key_lembrar), true);
         mEditor.commit();
     }else{
         mEditor.putString(getString(R.string.key_usuario), "");
         mEditor.commit();mEditor.putString(getString(R.string.key_senha), "");
         mEditor.commit();mEditor.putBoolean(getString(R.string.key_lembrar), false);
         mEditor.commit();
     }

    }

    private void verificarPreferencias() {
        usuario = mSharedPreferences.getString(getString(R.string.key_usuario),
                "");senha = mSharedPreferences.getString(getString(R.string.key_senha),
                "");
        boolean lembrar = mSharedPreferences.getBoolean(getString(R.string.key_lembrar),
                        false);
        if(lembrar){
            userEdit.setText(usuario);
            senhaEdit.setText(senha);
            checkBox.setChecked(true);
        }
    }

}
