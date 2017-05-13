package net.tecgurus.prueba_3.data;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class PruebaNueva
 */
@Stateless
@LocalBean
public class PruebaNueva {

    /**
     * Default constructor. 
     */
    public PruebaNueva() {
        // TODO Auto-generated constructor stub
    }
    
    public String saluda2(){
    	return "Nuevo saludo desde otro bean EJB";
    }

}
