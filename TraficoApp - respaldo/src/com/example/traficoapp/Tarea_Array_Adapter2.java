package com.example.traficoapp;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Tarea_Array_Adapter2  extends ArrayAdapter{

	
	 public Tarea_Array_Adapter2(Context context, List<Tarea> objects) {

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

	        titulo.setText(item.getnombre());
	        subtitulo.setText(item.gettelefono());
            // String subtitulox=(item.getstatus().toString().trim());
	       
	    			
	        
             String recoger=(item.getrecoger());
	        if(recoger.equals(recoger)){
	           
	        	  titulo.setTextColor(Color.parseColor("#FF0000")); //rojo
	        	  subtitulo.setTextColor(Color.parseColor("#FF0000")); //rojo
	        	  
	        	//listItemView.setBackgroundColor(Color.parseColor("#00FF00"));
	        	
			}else{
		         titulo.setTextColor(Color.parseColor("#111111")); //rojo
			     subtitulo.setTextColor(Color.parseColor("#111111")); //azul
				// titulo.setBackgroundColor(Color.parseColor("#111111"));
				//listItemView.setBackgroundColor(Color.parseColor("#00FF00"));

			}
	        //Devolver al ListView la fila creada
	        return listItemView;

	    }
}
