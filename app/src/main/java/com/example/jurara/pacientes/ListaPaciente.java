package com.example.jurara.pacientes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class ListaPaciente extends AppCompatActivity implements ListView.OnItemClickListener,ListView.OnItemLongClickListener{
    ListView lista;
    int idAux[];
    DBAdapter bd;
    String nombre[];
    int ids[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paciente);
        lista = (ListView) findViewById(R.id.lista);
        bd=new DBAdapter(getApplicationContext());
        llenarLista();
        lista.setOnItemClickListener(this);
        lista.setOnItemLongClickListener(this);


    }


    private void llenarLista(){
        bd.open();
        int n=bd.lengthPacientes();
         ids = new int[n];
         nombre = new String[n];
        String [] email = new String[n];
        String [] telefono = new String[n];

        int i=0;
        Cursor result=bd.getAllPacientesAZ();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            int id = result.getInt(0);
            String name=result.getString(1);
            String email1=result.getString(2);
            String phone=result.getString(3);

            ids[i]=id;
            nombre[i]=name;
            email[i]=email1;
            telefono[i]=phone;

            i++;

            result.moveToNext();
        }
        result.close();
        idAux=ids;
        ArrayAdapterListView adapter = new ArrayAdapterListView(getApplicationContext(), nombre,email,telefono);
        lista.setAdapter(adapter);

        bd.close();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getApplicationContext(),Medicar.class);


        intent.putExtra("ID",idAux[i]+"");
        intent.putExtra("NOMBRE",nombre[i]);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
        final EditText ET = new EditText(lista.getContext());
        final AlertDialog.Builder ADB = new AlertDialog.Builder(lista.getContext());
        lista.getSelectedItem();


        ADB.setTitle("Que hacer?");
        ADB.setMessage("Ver Medicamentos ó Editar Paciente?");




        ADB.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent=new Intent(getApplicationContext(),Alta_Cliente.class);
                intent.putExtra("ID",idAux[i]+"");

                startActivity(intent);

            }
        });


        ADB.setNegativeButton("Ver Medicamento", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent =new Intent(getApplicationContext(),VerMedicamentos.class);
                intent.putExtra("ID",idAux[i]+"");
                startActivity(intent);
            }
        });

        AlertDialog AD = ADB.create();
        AD.show();


        return true;
    }
}
