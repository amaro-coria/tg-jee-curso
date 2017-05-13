package net.tecgurus.prueba_3.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tecgurus.prueba_3.data.PruebaEJB;
import net.tecgurus.prueba_3.data.PruebaNueva;

/**
 * Servlet implementation class HolaMundoServlet
 */
public class HolaMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PruebaEJB instanciaInyectada;
	@EJB
	private PruebaNueva instancia2;

    /**
     * Default constructor. 
     */
    public HolaMundoServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Mensaje desde el EJB: ").append(instanciaInyectada.saludar() + " - OTRO SALUDO - "+instancia2.saluda2() 
		+ "OTRO MAS: ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
