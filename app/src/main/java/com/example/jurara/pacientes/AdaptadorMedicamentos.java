package com.example.jurara.pacientes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by jurara on 30/09/2017.
 */

public class AdaptadorMedicamentos extends BaseAdapter {
    Context context;

    String[] medicamento;
    String[] padecimiento;
    String[] instrucciones;
    String[] fechadeconsulta;
    String[] empezaratomar;
    String[] dejardetomar;
    int[] vigencia;

    LayoutInflater inflater;

    public AdaptadorMedicamentos(Context context, String[] medicamento, String[] padecimiento, String[] instrucciones, String[] fechadeconsulta, String[] empezaratomar, String[] dejardetomar,  int[] vigencia) {
        this.context = context;
        this.medicamento=medicamento;
        this.padecimiento= padecimiento;
        this.instrucciones= instrucciones;
        this.fechadeconsulta= fechadeconsulta;
        this.empezaratomar= empezaratomar;
        this.dejardetomar= dejardetomar;
        this.vigencia= vigencia;
    }



    @Override
    public int getCount() {
        return medicamento.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables

        TextView txtmedicamento;
        TextView txtpadecimiento;
        TextView txtinstrucciones;
        TextView txtfechadeconsulta;
        TextView txtempezaratomar;
        TextView txtdejardetomar;
        TextView txtvigencia;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item_medicamento, parent, false);

        // Locate the TextViews in listview_item.xml
        txtmedicamento = (TextView) itemView.findViewById(R.id.txtMedicamento);
        txtpadecimiento= (TextView) itemView.findViewById(R.id.txtPadecimiento);;
        txtinstrucciones= (TextView) itemView.findViewById(R.id.Instrucciones);;
        txtfechadeconsulta= (TextView) itemView.findViewById(R.id.Fechaconsulta);;
        txtempezaratomar= (TextView) itemView.findViewById(R.id.empezaratomar);;
        txtdejardetomar= (TextView) itemView.findViewById(R.id.DejardeTomar);;
        txtvigencia= (TextView) itemView.findViewById(R.id.txtVigencia);;



        // Capture position and set to the TextViews
         txtmedicamento.setText(medicamento[position]);
         txtpadecimiento.setText(padecimiento[position]);
         txtinstrucciones.setText(instrucciones[position]);
         txtfechadeconsulta.setText(fechadeconsulta[position]);
         txtempezaratomar.setText(empezaratomar[position]);
         txtdejardetomar.setText(dejardetomar[position]);
        if(vigencia[position]==1){
            txtvigencia.setText("Aun Vigente");
        }else{
            txtvigencia.setText("Ya no tomar: no vigente");
        }


        return itemView;
    }
}
