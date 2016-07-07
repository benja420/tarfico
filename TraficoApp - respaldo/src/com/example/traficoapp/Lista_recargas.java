package com.example.traficoapp;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Lista_recargas extends Activity {
	
	
	ArrayAdapter adaptador;
	private EditText txtChoferr;
	private ListView listView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recargas);
	
		txtChoferr=(EditText)findViewById(R.id.txtChoferr);
		listView1=(ListView)findViewById(R.id.listView1);
		
		  
        Bundle bundle = this.getIntent().getExtras();
        
        //Construimos el mensaje a mostrar
        txtChoferr.setText(bundle.getString("CHOFER"));
       
		
		TareaWSConsulta tarea = new TareaWSConsulta();
        tarea.execute();
	
	}
	
	
	//Tarea As�ncrona para llamar al WS de consulta en segundo plano
		private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {
			
			 List<Tarea> listaClientes;

			 
		    protected Boolean doInBackground(String... params) {
		    	
		    	boolean resul = true;
		 
		    	final String URL="http://201.159.106.93:8088/ServicioClientes.asmx?WSDL";
	            final String NAMESPACE = "http://benja420.org/";
	            final String METHOD_NAME ="ListadoClientes2";
	            final String SOAP_ACTION = "http://benja420.org/ListadoClientes2";

	       
	            
	            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	            request.addProperty("nombres", txtChoferr.getText().toString());
	            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	            envelope.dotNet = true;

	            envelope.setOutputSoapObject(request);

	            HttpTransportSE transporte = new HttpTransportSE(URL);

	            try
	            {
	                transporte.call(SOAP_ACTION, envelope);

	                SoapObject resSoap =(SoapObject)envelope.getResponse();
	                listaClientes = new ArrayList<Tarea>();

	                //listaClientes = new [resSoap.getPropertyCount()];
	                int size=resSoap.getPropertyCount();

	                for (int i = 0; i < size; i++)
	                {
	                    SoapObject ic = (SoapObject)resSoap.getProperty(i);

	                    //Clientes cli = new Clientes();
	                    Tarea cli = new Tarea();
	                    //cli.setid(Integer.parseInt(ic.getProperty(0).toString()));
	                   // cli.setnombre(ic.getProperty(0).toString());
	                    //cli.settelefono(ic.getProperty(1).toString());

	                    cli.setnombre(ic.getProperty(0).toString());//FACTURA
	                    cli.settelefono(ic.getProperty(1).toString());//NOMBRE
	                    cli.setpendiente(ic.getProperty(2).toString());//CHOFER
	                    cli.setstatus(ic.getProperty(3).toString());//TOTAL
	                    cli.settotal(ic.getProperty(4).toString());//OBSDIRECCION
	                   /* cli.setdireccion(ic.getProperty(5).toString());
	                    cli.setrecoger(ic.getProperty(6).toString());
	                    cli.setsufijo(ic.getProperty(7).toString());*/
	                    listaClientes.add(cli);

	                }

	                if(listaClientes.size() == 0)
	                {
	                    resul=false;
	                }
				} 
				catch (Exception e) 
				{
					resul = false;
				} 
		 
		        return resul;
		    }
		    
		    protected void onPostExecute(Boolean result) {
		    	
		    	if (result)
		    	{
		    		
		    		
		    		//Inicializar el adaptador con la fuente de datos
	                adaptador = new Tarea_Array_Adapter2(Lista_recargas.
	                        this,listaClientes
	                        );

	                //Relacionando la lista con el adaptador
	                listView1.setAdapter(adaptador);

	                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	                    @Override
	                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

	                        Tarea tareaActual = (Tarea) adaptador.getItem(position);
	                        String msg = "Elegiste el Cliente:\n" + tareaActual.gettelefono() + "-" + tareaActual.gettotal();
	                        Toast.makeText(Lista_recargas.this, msg, Toast.LENGTH_LONG).show();
	                        Toast.makeText(Lista_recargas.this, msg, Toast.LENGTH_LONG).show();
	                        
	                       

	                       //Creamos el Intent
	                        Intent intent =
	                                new Intent(Lista_recargas.this,Detalle_recargas.class);

	                        //Creamos la información a pasar entre actividades
	                        Bundle b = new Bundle();
	                        b.putString("FACTURA",tareaActual.getnombre());

	                        Bundle b2 = new Bundle();
	                        b2.putString("NOMBRE",tareaActual.gettelefono());

	                        Bundle b3 =new Bundle();
	                        b.putString("DIRECCION",tareaActual.gettotal());
	                    
	                                              
	                     /*   Bundle b4=new Bundle();
	                        b.putString("CHOFER", txtChofer.getText().toString());*/
	                        
	                        Bundle b5 =new Bundle();
	                        b.putString("TOTAL",tareaActual.getstatus());
	                        
	                        Bundle b6=new Bundle();
	                        b.putString("CHOFER", txtChoferr.getText().toString());
	                        
	                        //Añadimos la información al intent
	                        intent.putExtras(b);
	                        intent.putExtras(b2);
	                        intent.putExtras(b3);
	                       //intent.putExtras(b4);
	                        intent.putExtras(b5);
	                        intent.putExtras(b6);
	                        //Iniciamos la nueva actividad
	                        startActivity(intent);

	             

	                    }
	                    
	                });

		    	}
		    	else
		    	{
		    		Toast.makeText(Lista_recargas.this, "No Hay Registros Para Este Chofer", Toast.LENGTH_LONG).show();
		    		//txtResultado.setText("Error!");
		    	}
	                } 
		}
	

}
