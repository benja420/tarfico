package com.example.traficoapp;

public class Tarea {

	
	  private int id ;
	    private String nombre;
	    private String telefono;
	    private String pendiente; 
        private String status;
        private String total;
	   private String direccion;
	   private String recoger;
	   private String comentario;
	   private String sufijo;
	   
        public Tarea()
	    {
	       id =0;
	        nombre = "";
	        telefono = "";
	        pendiente="";
	       status="";
	        total="";
	        direccion="";
	        recoger="";
	        comentario="";
	        sufijo="";
	    }

	   
        public Tarea(int id, String nombre, String telefono,String pendiente,String status,String total,String direccion,String recoger,String comentario,String sufijo){
	       this.id = id;
	        this.nombre = nombre;
	        this.telefono = telefono;
	        this.pendiente= pendiente;
	        this.status=status;
	        this.total=total;
	        this.direccion=direccion;
	        this.recoger=recoger;
	        this.comentario=comentario;
	        this.sufijo=sufijo;
	    }

	  
        public void setid(int id){
	       this.id = id;
	   }

	    public void setnombre(String nombre){
	        this.nombre =nombre;
	    }

	    public void settelefono(String telefono){
	        this.telefono=telefono;
	    }
	    
	    public void setpendiente(String pendiente){
	        this.pendiente=pendiente;
	    }

	    public void setstatus(String status){
	        this.status=status;
	    }
	   
	    public void settotal(String total){
	        this.total=total;
	    }
	    
	    public void setdireccion(String direccion){
	        this.direccion=direccion;
	    }
	    
	    public void setrecoger(String recoger){
	        this.recoger=recoger;
	    }
	    
	    public void setcomentario(String comentario){
	        this.comentario=comentario;
	    }
	    
	    public void setsufijo(String sufijo){
	        this.sufijo=sufijo;
	    }
	    
	    public int getid(){return id;}
	    public String getnombre(){return nombre;}
	    public String gettelefono(){return telefono;}
	    public String getpendiente(){return pendiente;}
	    public String getstatus(){return status;}
	    public String gettotal(){return total;}
	    public String getdireccion(){return direccion;}
	    public String getrecoger(){return recoger;}
	    public String getcomentario(){return comentario;}
	    public String getsufijo(){return sufijo;}
}
