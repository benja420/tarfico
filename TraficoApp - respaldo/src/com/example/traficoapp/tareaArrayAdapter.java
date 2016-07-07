package com.example.traficoapp;

import java.util.List;



import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class tareaArrayAdapter extends ArrayAdapter {
	
	 public tareaArrayAdapter(Context context, List<Tarea> objects) {

	        super(context, 0, objects);
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent){

	        //Obteniendo una instancia del inflater
	        LayoutInflater inflater = (LayoutInflater)getContext()
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	        //Salvando la referencia del View de la fila
	        View listItemView = convertView;

	        //Comprobando si el View no existe
	        if (null == convertView) {
	            //Si no existe, entonces inflarlo con two_line_list_item.xml
	            listItemView = inflater.inflate(
	                    android.R.layout.two_line_list_item,
	                    parent,
	                    false);
	        }

	        //Obteniendo instancias de los text views
	        TextView titulo = (TextView)listItemView.findViewById(android.R.id.text1);
	        TextView subtitulo = (TextView)listItemView.findViewById(android.R.id.text2);
	       
	        //Obteniendo instancia de la Tarea en la posición actual
	        Tarea item = (Tarea)getItem(position);

	        //Dividir la cadena en Nombre y Hora
	       // String cadenaBruta;
	       // String subCadenas [];
	       // String delimitador = ",";

	       // cadenaBruta = item.toString();
	        //subCadenas = cadenaBruta.split(delimitador,2);

	       /* titulo.setText(item.getnombre());
	        subtitulo.setText(item.gettelefono());
            String subtitulo2=(item.getpendiente());
	        if(item.getpendiente().equals("SI")){
	        	 titulo.setPaintFlags( titulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
	        	 subtitulo.setPaintFlags( titulo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
	        	titulo.setTextColor(Color.parseColor("#111111")); //VERDE
	        	subtitulo.setTextColor(Color.parseColor("#111111")); //VERDE
	        	//listItemView.setBackgroundColor(Color.parseColor("#FF0000"));
	        	
			}else{
				 //subtitulo.setPaintFlags( subtitulo.getPaintFlags() &~Paint.STRIKE_THRU_TEXT_FLAG);
				// subtitulo.setTextColor(Color.parseColor("#FF0000")); //ROJO#FF0000
				// subtitulo.setBackgroundColor(Color.parseColor("#607D8B"));
				 

			}*/
	       subtitulo.setBackgroundColor(Color.parseColor("#FF0000")); //ROJO#FF0000
		    subtitulo.setBackgroundColor(Color.parseColor("#607D8B"));
	       // titulo.setTextColor(Color.parseColor("#111111")); //VERDE
        	//subtitulo.setTextColor(Color.parseColor("#111111")); //VERDE
	        //Devolver al ListView la fila creada
	        return listItemView;

	    }

}
