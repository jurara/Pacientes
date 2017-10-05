package com.example.jurara.pacientes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class VerMedicamentos extends AppCompatActivity implements ListView.OnItemLongClickListener {
    DBAdapter bd;
    ListView lista;
    String[] medicamento,nombre;
    int[] idAux;
    String id;
    int[] ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_medicamentos);
        bd = new DBAdapter(getApplicationContext());
        lista=(ListView)findViewById(R.id.lista);
        Intent intent=getIntent();
         id=intent.getStringExtra("ID");
        llenarLista2();
        lista.setOnItemLongClickListener(this);

    }


    private void llenarLista2(){
        bd.open();
        int n=bd.lengthMedicamentos(Integer.parseInt(id));
        ids = new int[n];
        nombre = new String[n];
        medicamento = new String[n];
        String[] padecimiento=new String[n];
        String[] ins=new String[n];
        String[] fc=new String[n];
        String[] et=new String[n];
        String[] dt=new String[n];
        int[] v=new int[n];

        int i=0;
        Cursor result=bd.getAllMedicamentosAZ(Integer.parseInt(id));
        result.moveToFirst();
        while (!result.isAfterLast()) {
            int id = result.getInt(0);
            String med=result.getString(1);
            String pa=result.getString(2);
            String inst=result.getString(3);
            String fecon=result.getString(4);
            String emptom=result.getString(5);
            String dejar=result.getString(6);
            int vi=result.getInt(7);

            ids[i]=id;
            medicamento[i]=med;
            padecimiento[i]=pa;
            ins[i]=inst;
            fc[i]=fecon;
            et[i]=emptom;
            dt[i]=dejar;
            v[i]=vi;

            i++;

            result.moveToNext();
        }
        result.close();
        idAux=ids;
        AdaptadorMedicamentos adapter = new AdaptadorMedicamentos(getApplicationContext(),medicamento,padecimiento,ins,fc,et,dt,v);
        lista.setAdapter(adapter);

        bd.close();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getApplicationContext(),Medicar.class);
        intent.putExtra("IDM",idAux[i]+"");
        intent.putExtra("IDP",id+"");
        startActivity(intent);
        return false;
    }
}