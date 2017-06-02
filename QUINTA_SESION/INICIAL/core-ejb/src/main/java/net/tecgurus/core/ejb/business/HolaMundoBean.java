package net.tecgurus.core.ejb.business;

import javax.ejb.Stateless;
import net.tecgurus.core.ejb.business.interf.HolaMundo;

/**
 * Session Bean implementation class HolaMundoBean
 */
@Stateless
public class HolaMundoBean implements HolaMundo {

	@Override
	public String saluda(){
	   return "Hola mundo EJB";
   }

}
