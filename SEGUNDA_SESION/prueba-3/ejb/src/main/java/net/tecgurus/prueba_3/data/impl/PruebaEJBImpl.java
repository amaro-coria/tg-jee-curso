package net.tecgurus.prueba_3.data.impl;

import javax.ejb.Stateless;
import net.tecgurus.prueba_3.data.PruebaEJB;

/**
 * Session Bean implementation class PruebaEJBImpl
 */
@Stateless
public class PruebaEJBImpl implements PruebaEJB {

    /**
     * Default constructor. 
     */
    public PruebaEJBImpl() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String saludar(){
    	return "Hola Mundo ";
    }

}
