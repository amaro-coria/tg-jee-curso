package net.tecgurus.business.impl;

import javax.ejb.Stateful;
import net.tecgurus.business.TestSession;

/**
 * Session Bean implementation class TestSessionBean
 */
@Stateful
public class TestSessionBean implements TestSession {
	
	private String lastAction;

    /**
     * Default constructor. 
     */
    public TestSessionBean() {
        // TODO Auto-generated constructor stub
    }
    
    public void setAction(String action){
    	this.lastAction = action;
    }
    
    public String getLastAction(){
    	return this.lastAction;
    }

}
