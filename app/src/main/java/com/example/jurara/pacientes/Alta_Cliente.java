package com.example.jurara.pacientes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Alta_Cliente extends AppCompatActivity implements View.OnClickListener {
    EditText nom,dir,cel,corr,fec;
    int dia,mes,ano;
    Calendar calendar;
    Button btnG;
    DBAdapter db;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta__cliente);
        nom = (EditText) findViewById(R.id.txtNombre);
        dir = (EditText) findViewById(R.id.txtDireccion);
        cel = (EditText) findViewById(R.id.txtNoCelular);
        corr = (EditText) findViewById(R.id.txtCorreo);
        fec = (EditText) findViewById(R.id.txtFecha);
        btnG=(Button)findViewById(R.id.btnReg);

        calendar = GregorianCalendar.getInstance();

        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);
        fec.setOnClickListener(this);
        btnG.setOnClickListener(this);

        db=new DBAdapter(this);

            Intent intent = getIntent();
            id = Integer.parseInt(intent.getStringExtra("ID"));


    }


    public void guardarCliente(){
        String n=nom.getText().toString();
        String d=dir.getText().toString();
        String c=cel.getText().toString();
        String cor=corr.getText().toString();
        String f=fec.getText().toString();
        if(!(n.isEmpty() || d.isEmpty() || c.isEmpty() || cor.isEmpty() || f.isEmpty())){
            db.open();

            db.insertPaciente(n,d,c,cor,f);
            db.close();
            Toast.makeText(getApplicationContext(),"Cliente Guardado",Toast.LENGTH_SHORT).show();
            Intent in=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }else{
            Toast.makeText(getApplicationContext(),"Llene todos los Campos",Toast.LENGTH_SHORT).show();
        }


    }

    public void dataP(){
        final DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i2, int i1, int i) {
                fec.setText(i+"-"+(i1)+"-"+i2);
            }
        },ano,mes,dia);
        datePickerDialog.show();
    }

    public void actualizarcliente(){
        String n=nom.getText().toString();
        String d=dir.getText().toString();
        String c=cel.getText().toString();
        String cor=corr.getText().toString();
        String f=fec.getText().toString();
        if(!(n.isEmpty() || d.isEmpty() || c.isEmpty() || cor.isEmpty() || f.isEmpty())){
            db.open();
            db.updatePaciente(id,n,d,c,cor,f);
            db.close();
            Toast.makeText(getApplicationContext(),"Cliente Actualizado",Toast.LENGTH_SHORT).show();
            Intent in=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }else{
            Toast.makeText(getApplicationContext(),"Llene todos los Campos",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(final View view) {
        if(view==fec){//fecha
            dataP();
        }
        if(view==btnG){//boton Guardar
            if(id!=0){
                actualizarcliente();
            }else {
                guardarCliente();
            }
        }
    }
}
