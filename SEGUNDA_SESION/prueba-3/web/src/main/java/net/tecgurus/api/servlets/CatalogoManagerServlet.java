package net.tecgurus.api.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tecgurus.dao.CatalogoGeneraDAO;
import net.tecgurus.entidades.Catalogo_General;

/**
 * Servlet implementation class CatalogoManagerServlet
 */
public class CatalogoManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogoGeneraDAO daoCatGeneral;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatalogoManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operacion = request.getParameter("op");
		PrintWriter pw = response.getWriter();
		if (operacion.equalsIgnoreCase("LISTAR")) {
			List<Catalogo_General> listaBD = daoCatGeneral.findAll();
			for (Catalogo_General c : listaBD) {
				pw.write("Catalogo: [" + c.getIdCat() + " , " + c.getDscCorCat() + " , " + c.getDscComCat() + "] \n");
			}
		} else {
			pw.write("Operacion no reconocida!!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
