package com.example.jurara.pacientes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class Medicar extends AppCompatActivity implements View.OnClickListener{
    EditText medicamento,padecimiento,instrucciones,feccon,fecini,fecfin;
    CheckBox vigencia;
    Button btnmedicar;
    Calendar calendar;
    int dia,mes,ano;
    String paciente;
    String id,idm,idp;
    DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicar);
        medicamento=(EditText)findViewById(R.id.txtNombreM);
        padecimiento=(EditText)findViewById(R.id.txtPadecimiento);
        instrucciones=(EditText)findViewById(R.id.txtInstruc);
        feccon=(EditText)findViewById(R.id.txtFechaCon);
        fecini=(EditText)findViewById(R.id.txtFechaEmpezar);
        fecfin=(EditText)findViewById(R.id.txtFinalizar);
        vigencia=(CheckBox)findViewById(R.id.checkBox);
        btnmedicar=(Button)findViewById(R.id.btnRecetar);

        calendar = GregorianCalendar.getInstance();

        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        ano = calendar.get(Calendar.YEAR);
        feccon.setOnClickListener(this);
        fecini.setOnClickListener(this);
        fecfin.setOnClickListener(this);
        btnmedicar.setOnClickListener(this);
        Intent intent=getIntent();


        id=intent.getStringExtra("ID");
        idm=intent.getStringExtra("IDM");
        idp=intent.getStringExtra("IDP");
        paciente=intent.getStringExtra("NOMBRE");
        Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_SHORT).show();

        db=new DBAdapter(this);

    }

    public void dataP(final EditText e){
        final DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i2, int i1, int i) {
                e.setText(i+"-"+(i1)+"-"+i2);
            }
        },ano,mes,dia);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        if(view==feccon){
            dataP(feccon);
        }
        if (view==fecini){
            dataP(fecini);
        }
        if (view==fecfin){
            dataP(fecfin);
        }
        if (view==btnmedicar){
            if(Integer.parseInt(idm)!=0){
                actualizarm();
            }else {
                Medicar();
            }
        }
    }

    public void Medicar(){
        medicamento=(EditText)findViewById(R.id.txtNombreM);
        padecimiento=(EditText)findViewById(R.id.txtPadecimiento);
        instrucciones=(EditText)findViewById(R.id.txtInstruc);
        feccon=(EditText)findViewById(R.id.txtFechaCon);
        fecini=(EditText)findViewById(R.id.txtFechaEmpezar);
        fecfin=(EditText)findViewById(R.id.txtFinalizar);
        vigencia=(CheckBox)findViewById(R.id.checkBox);
        String m=medicamento.getText().toString();
        String p=padecimiento.getText().toString();
        String i=instrucciones.getText().toString();
        String fc=feccon.getText().toString();
        String fi=fecini.getText().toString();
        String ff=fecfin.getText().toString();
        int v=0;
        if(vigencia.isChecked()){
            v=1;
        }else{
            v=0;
        }
        if(!(m.isEmpty() || p.isEmpty() || i.isEmpty() || fc.isEmpty() || fi.isEmpty() || ff.isEmpty() )){
            db.open();
           // Toast.makeText(getApplicationContext(),v+"",Toast.LENGTH_SHORT).show();
            db.insertMedicamento(Integer.parseInt(id),m,p,i,fc,fi,ff,v);
            db.close();
            Toast.makeText(getApplicationContext(),"Receta Guardada Para el Paciente:\n"+paciente,Toast.LENGTH_SHORT).show();
            Intent inte=new Intent(getApplicationContext(),ListaPaciente.class);
            startActivity(inte);
        }else{
            Toast.makeText(getApplicationContext(),"llene todos los campos",Toast.LENGTH_SHORT).show();
        }


    }

    public void actualizarm(){
        medicamento=(EditText)findViewById(R.id.txtNombreM);
        padecimiento=(EditText)findViewById(R.id.txtPadecimiento);
        instrucciones=(EditText)findViewById(R.id.txtInstruc);
        feccon=(EditText)findViewById(R.id.txtFechaCon);
        fecini=(EditText)findViewById(R.id.txtFechaEmpezar);
        fecfin=(EditText)findViewById(R.id.txtFinalizar);
        vigencia=(CheckBox)findViewById(R.id.checkBox);
        String m=medicamento.getText().toString();
        String p=padecimiento.getText().toString();
        String i=instrucciones.getText().toString();
        String fc=feccon.getText().toString();
        String fi=fecini.getText().toString();
        String ff=fecfin.getText().toString();
        int v=0;
        if(vigencia.isChecked()){
            v=1;
        }else{
            v=0;
        }
        if(!(m.isEmpty() || p.isEmpty() || i.isEmpty() || fc.isEmpty() || fi.isEmpty() || ff.isEmpty() )){
            db.open();
            // Toast.makeText(getApplicationContext(),v+"",Toast.LENGTH_SHORT).show();
            db.updateMedicamento(Integer.parseInt(idm),Integer.parseInt(idp),m,p,i,fc,fi,ff,v);

            db.close();
            Toast.makeText(getApplicationContext(),"Receta Actualizada para el paciente:\n"+paciente,Toast.LENGTH_SHORT).show();
            Intent inte=new Intent(getApplicationContext(),ListaPaciente.class);
            startActivity(inte);
        }else{
            Toast.makeText(getApplicationContext(),"llene todos los campos",Toast.LENGTH_SHORT).show();
        }


    }
}
