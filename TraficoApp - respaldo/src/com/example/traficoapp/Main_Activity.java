package com.example.traficoapp;



import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Activity extends Activity{

	    private EditText txtBuscar;
	    private TextView txtfactura;
		private TextView txtCliente;
		private TextView txtChofer2;
		private EditText txtChofer;
		private Spinner  Combo;
		private EditText txtComentario;
		private Button btnGuardar;
		
		private Button btnBuscar;
		private Button btnRecargas;
		ArrayAdapter adaptador;
		public String msje="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		txtfactura=(TextView)findViewById(R.id.txtfactura);
		txtCliente=(TextView)findViewById(R.id.txtCliente);
		txtChofer=(EditText)findViewById(R.id.txtChofer);
		txtChofer2=(TextView)findViewById(R.id.txtChofer2);
		Combo=(Spinner)findViewById(R.id.spinner);
		txtComentario=(EditText)findViewById(R.id.txtComentario);
		txtBuscar=(EditText)findViewById(R.id.txtBuscar);
		btnBuscar=(Button)findViewById(R.id.btnBuscar);
		btnRecargas=(Button)findViewById(R.id.btnRecargas);
	  
		btnGuardar=(Button)findViewById(R.id.btnGuardar);
		
		
		
		//String result=null;
				ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
						getBaseContext(), R.array.tipo_pago,
						android.R.layout.simple_spinner_item);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				Combo.setAdapter(adapter);
		
				 btnBuscar.setOnClickListener(new OnClickListener() {
		    			
		    			@Override
		    			public void onClick(View v) {

		    				TareaWSConsulta tarea = new TareaWSConsulta();
		    		        tarea.execute();
		    				
		    				
		    			}
		    		});
				 
				 
				
				

					
				 
				 btnRecargas.setOnClickListener(new OnClickListener() {
		    			
		    			@Override
		    			public void onClick(View v) {

		    				  //Creamos el Intent
	                        Intent intent =
	                                new Intent(Main_Activity.this,Lista_recargas.class);

	                        //Creamos la informaci贸n a pasar entre actividades
	                        Bundle b = new Bundle();
	                        b.putString("CHOFER",txtChofer.getText().toString());
       	                        
	                        //A帽adimos la informaci贸n al intent
	                        intent.putExtras(b);
	                      
	                        //Iniciamos la nueva actividad
	                        startActivity(intent);

		    				
		    				
		    			}
		    		});
				 

				 btnGuardar.setOnClickListener(new OnClickListener() {
		    			
		    			@Override
		    			public void onClick(View v) {

		    				TareaWSConsulta2 tarea = new TareaWSConsulta2();
		    		        tarea.execute();
		    				
		    				
		    			}
		    		});
				 
				 
				 
	}//fin onCreateBundle
	
	
	//Tarea As韓crona para llamar al WS de consulta en segundo plano
		private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {
			
			 List<Tarea> listaClientes;

			 
		    protected Boolean doInBackground(String... params) {
		    	
		    	boolean resul = true;
		 
		    	final String URL="http://201.159.106.93:8088/ServicioClientes.asmx?WSDL";
	            final String NAMESPACE = "http://benja420.org/";
	            final String METHOD_NAME ="ListadoClientes";
	            final String SOAP_ACTION = "http://benja420.org/ListadoClientes";

	       
	            
	            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	            request.addProperty("nombre", txtChofer.getText().toString());
	            request.addProperty("factura", txtBuscar.getText().toString());
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

	                    //Clientes clip = new Clientes();
	                    Tarea cli = new Tarea();
	                    //cli.setid(Integer.parseInt(ic.getProperty(0).toString()));
	                   // cli.setnombre(ic.getProperty(0).toString());
	                    //cli.settelefono(ic.getProperty(1).toString());

	                    cli.setnombre(ic.getProperty(0).toString());
	                    cli.settelefono(ic.getProperty(1).toString());
	                    cli.setpendiente(ic.getProperty(2).toString());
	                    cli.setstatus(ic.getProperty(3).toString());
	                    cli.settotal(ic.getProperty(4).toString());
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
		    		
		    		  String factura=null;
		    		  String nombre=null;
		    		  String chofer=null;
		    		  String total=null;
		    		  String cliente=null;
      	    		
      	    		for(int x=0;x<listaClientes.size();x++) {
      		    		factura =(listaClientes.get(x).getnombre().toString());
                        cliente=(listaClientes.get(x).gettelefono().toString());
                        chofer=(listaClientes.get(x).getstatus().toString());
                        total=(listaClientes.get(x).getpendiente().toString());
                           
     		    		}

      	    		txtfactura.setText(factura);
      	    		txtCliente.setText(cliente);
      	    		txtChofer.setText(chofer);
      	    		txtChofer2.setText(total);
      	    		

		    	}
		    	else
		    	{
		    		Toast.makeText(Main_Activity.this, "Esa Factura Ya Tiene Movimiento", Toast.LENGTH_LONG).show();
		    		//txtResultado.setText("Error!");
		    	}
	                } 
		}

		
	
		
		//Tarea As韓crona para llamar al WS de consulta en segundo plano
				private class TareaWSConsulta2 extends AsyncTask<String,Integer,Boolean> {
					
					 List<Tarea> listaClientes;

					 
				    protected Boolean doInBackground(String... params) {
				    	
				    	boolean resul = true;
				 
				    	final String URL="http://201.159.106.93:8088/ServicioClientes.asmx?WSDL";
			            final String NAMESPACE = "http://benja420.org/";
			            final String METHOD_NAME ="ActualizaTraficoDBF";
			            final String SOAP_ACTION = "http://benja420.org/ActualizaTraficoDBF";

			       
			            
			            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			            request.addProperty("nombre", txtChofer.getText().toString());
			            request.addProperty("factura", txtBuscar.getText().toString());
			            request.addProperty("conceptoases", Combo.getSelectedItem());
			            request.addProperty("comentario", txtComentario.getText().toString());
			            request.addProperty("sufijo", "" );
			            
			           
			            
			            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			            envelope.dotNet = true;

			            envelope.setOutputSoapObject(request);

			            HttpTransportSE transporte = new HttpTransportSE(URL);

			            try
			            {
			                transporte.call(SOAP_ACTION, envelope);
			              
			                SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
			                String res = resultado_xml.toString();
			                msje=res;

			                if(msje.equals("Datos Actualizados")) {
			                  resul=true;
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
				    		 // Toast.makeText(getBaseContext(), res+"",Toast.LENGTH_SHORT).show();
			                Toast.makeText(Main_Activity.this, "Datos Actualizados", Toast.LENGTH_LONG).show();
			              
			                  /*String factura=null;
				    		  String nombre=null;
				    		  String chofer=null;
				    		  String total=null;
				    		  String cliente=null;
		      	    		
		      	    		for(int x=0;x<listaClientes.size();x++) {
		      		    		factura =(listaClientes.get(x).getnombre().toString());
		                        cliente=(listaClientes.get(x).gettelefono().toString());
		                        chofer=(listaClientes.get(x).getstatus().toString());
		                        total=(listaClientes.get(x).getpendiente().toString());
		                           
		     		    		}

		      	    		txtfactura.setText(factura);
		      	    		txtCliente.setText(cliente);
		      	    		txtChofer.setText(chofer);*/
		      	    		
		      	    		
				    		/*//Inicializar el adaptador con la fuente de datos
			                adaptador = new tareaArrayAdapter(Activity_main.
			                    //    this,listaClientes
			                        );
		*/
			                //Relacionando la lista con el adaptador
			               /* lstClientes.setAdapter(adaptador);

			                lstClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			                    @Override
			                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			                        Tarea tareaActual = (Tarea) adaptador.getItem(position);
			                        String msg = "Elegiste el Cliente:\n" + tareaActual.getnombre() + "-" + tareaActual.gettelefono();
			                        Toast.makeText(Main_Activity.this, msg, Toast.LENGTH_LONG).show();
			                        
			                      

			                        //Creamos el Intent
			                       /* Intent intent =
			                                new Intent(Main_Activity.this,Detalle_pedido.class);*/

			                        /*//Creamos la informaci贸n a pasar entre actividades
			                        Bundle b = new Bundle();
			                        b.putString("NOMBRE",tareaActual.getnombre());

			                        Bundle b2 = new Bundle();
			                        b2.putString("TELEFONO",tareaActual.gettelefono());

			                        Bundle b3 =new Bundle();
			                        b.putString("TOTAL",tareaActual.gettotal());
			                    
			                                              
			                        Bundle b4=new Bundle();
			                        b.putString("CHOFER", txtfactura.getText().toString());
			                        
			                        Bundle b5 =new Bundle();
			                        b.putString("DIRECCION",tareaActual.getdireccion());
			                        
			                        Bundle b6=new Bundle();
			                        b.putString("SUFIJO", tareaActual.getsufijo());
			                        
			                        //A帽adimos la informaci贸n al intent
			                        intent.putExtras(b);
			                        intent.putExtras(b2);
			                        intent.putExtras(b3);
			                        intent.putExtras(b4);
			                        intent.putExtras(b5);
			                        intent.putExtras(b6);
			                        //Iniciamos la nueva actividad
			                        startActivity(intent);*/

			             

			                    
			                    
			                

				    	}
				    	else
				    	{
				    		Toast.makeText(Main_Activity.this, "Datos no Actualizados", Toast.LENGTH_LONG).show();
				    		
				    		//txtResultado.setText("Error!");
				    	}
			                } 
				}

	
				
		
}
