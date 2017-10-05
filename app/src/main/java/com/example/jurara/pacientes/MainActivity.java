package com.example.jurara.pacientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void registrarPaciente(View view){
        Intent intent=new Intent(getApplicationContext(),Alta_Cliente.class);
        intent.putExtra("ID",""+0);
        startActivity(intent);
    }

    public void receta(View view){
        Intent intent=new Intent(getApplicationContext(),Medicar.class);
        startActivity(intent);
    }

    public void verPacientes(View view){
        Intent intent=new Intent(getApplicationContext(),ListaPaciente.class);
        startActivity(intent);
    }



}
