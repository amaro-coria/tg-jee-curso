package net.tecgurus.servicios;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class PruebaExterna
 */
@Stateless
@LocalBean
public class PruebaExterna {

    /**
     * Default constructor. 
     */
    public PruebaExterna() {
        // TODO Auto-generated constructor stub
    }
    
    public String otroMas(){
    	return "HOLA MUNDO 3";
    }

}
