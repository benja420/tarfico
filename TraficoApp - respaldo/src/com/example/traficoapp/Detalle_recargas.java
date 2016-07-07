package com.example.traficoapp;

import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Detalle_recargas extends Activity {

	    public String msje;
	    private TextView txtfacturaDR;
		private TextView txtClienteDR;
		private TextView txtDir;
		private EditText txtChoferDR;
		private Spinner  ComboDR;
		private EditText txtComentarioDR;
		private Button btnGuardarDR;
		private Button btnSalirDR;
		
		ArrayAdapter adaptador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_recarga);
		
		txtfacturaDR=(TextView)findViewById(R.id.txtfacturaDR);
		txtClienteDR=(TextView)findViewById(R.id.txtClienteDR);
		txtChoferDR=(EditText)findViewById(R.id.txtChoferDR);
		txtDir=(TextView)findViewById(R.id.txtDir);
		ComboDR=(Spinner)findViewById(R.id.spinnerDR);
		txtComentarioDR=(EditText)findViewById(R.id.txtComentarioDR);
		btnSalirDR=(Button)findViewById(R.id.btnSalirDR);
		btnGuardarDR=(Button)findViewById(R.id.btnGuardarDR);
		
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getBaseContext(), R.array.tipo_pago,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		ComboDR.setAdapter(adapter);
		
		Bundle bundle =this.getIntent().getExtras();
		
		txtChoferDR.setText(bundle.getString("CHOFER"));
		txtfacturaDR.setText(bundle.getString("FACTURA"));
		txtDir.setText(bundle.getString("DIRECCION"));
		txtClienteDR.setText(bundle.getString("NOMBRE"));
		
		 btnGuardarDR.setOnClickListener(new OnClickListener() {
 			
 			@Override
 			public void onClick(View v) {

 				TareaWSConsulta tarea = new TareaWSConsulta();
 		        tarea.execute();
 				
 				
 			}
 		});
		 
		 btnSalirDR.setOnClickListener(new OnClickListener() {
	 			
	 			@Override
	 			public void onClick(View v) {

	 				finish();
	 				
	 				
	 			}
	 		});
	}
	

	
	
	
	//Tarea Asíncrona para llamar al WS de consulta en segundo plano
			private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {
				
				 List<Tarea> listaClientes;

				 
			    protected Boolean doInBackground(String... params) {
			    	
			    	boolean resul = true;
			 
			    	final String URL="http://201.159.106.93:8088/ServicioClientes.asmx?WSDL";
		            final String NAMESPACE = "http://benja420.org/";
		            final String METHOD_NAME ="ActualizaTraficoDBF";
		            final String SOAP_ACTION = "http://benja420.org/ActualizaTraficoDBF";

		       
		            
		            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		            request.addProperty("nombre", txtChoferDR.getText().toString());
		            request.addProperty("factura", txtfacturaDR.getText().toString());
		            request.addProperty("conceptoases", ComboDR.getSelectedItem());
		            request.addProperty("comentario", txtComentarioDR.getText().toString());
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
		                Toast.makeText(Detalle_recargas.this, "Datos Actualizados", Toast.LENGTH_LONG).show();
			    	}
			    	else
			    	{
			    		Toast.makeText(Detalle_recargas.this, "Datos no Actualizados", Toast.LENGTH_LONG).show();
			    		//txtResultado.setText("Error!");
			    	}
		                } 
			}

	
	
	
}
