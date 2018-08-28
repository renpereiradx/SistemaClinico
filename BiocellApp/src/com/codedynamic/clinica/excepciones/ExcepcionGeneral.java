package com.codedynamic.clinica.excepciones;

public class ExcepcionGeneral extends RuntimeException{
	 public ExcepcionGeneral() {
	        this("Ocurrio una excepcion");
	    }
	    public ExcepcionGeneral(String mensaje) {
	        super(mensaje);
	    }
}
