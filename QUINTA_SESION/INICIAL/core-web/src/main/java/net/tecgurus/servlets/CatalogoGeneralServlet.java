package net.tecgurus.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tecgurus.business.CatalogoBusiness;
import net.tecgurus.common.dto.CataDTO;

/**
 * Servlet implementation class CatalogoGeneralServlet
 */
public class CatalogoGeneralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CatalogoBusiness serviceCata;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 - Definir el tipo de operacion que se necesita realizar
		String opType = request.getParameter("op");
		if (opType == null) {
			opType = "NA";
		}
		// op = 'create' es alta, op='update' es actualizacion, op='delete' es
		// borrado
		switch (opType) {
		case "create":
			// Obtener los 5 parametros necesarios
			String dscComCat = request.getParameter("dsc");
			String dscCorCat = request.getParameter("dsc2");
			String fchCre = request.getParameter("epoch1");
			String fchModi = request.getParameter("epoch2");
			String idEstCat = request.getParameter("status");
			//
			if (dscComCat == null || dscCorCat == null || fchCre == null || fchModi == null || idEstCat == null) {
				// TODO ver que le vamos a mandar como respuesta en el servlet
			}
			CataDTO objetoNuevo = new CataDTO();
			objetoNuevo.setDscComCat(dscComCat);
			objetoNuevo.setDscCorCat(dscCorCat);
			objetoNuevo.setFchCre(new Date(Long.parseLong(fchCre)*1000));
			objetoNuevo.setFchMod(new Date(Long.parseLong(fchModi)*1000));
			objetoNuevo.setIdEstCat(Integer.parseInt(idEstCat));
			serviceCata.createNewRecord(objetoNuevo);
			response.getWriter().println("Objeto guardado en BD");
			break;
		case "update":
			String dscComCatUpdate = request.getParameter("dsc");
			String dscCorCatUpdate = request.getParameter("dsc2");
			String fchCreUpdate = request.getParameter("epoch1");
			String fchModiUpdate = request.getParameter("epoch2");
			String idEstCatUpdate = request.getParameter("status");
			String id = request.getParameter("id");
			//
			if (dscComCatUpdate == null || dscCorCatUpdate == null || fchCreUpdate == null || fchModiUpdate == null
					|| idEstCatUpdate == null) {
				// TODO ver que le vamos a mandar como respuesta en el servlet
			}
			CataDTO objetoUpdate = new CataDTO();
			objetoUpdate.setDscComCat(dscComCatUpdate);
			objetoUpdate.setDscCorCat(dscCorCatUpdate);
			objetoUpdate.setFchCre(new Date(Long.parseLong(fchCreUpdate)*1000));
			objetoUpdate.setFchMod(new Date(Long.parseLong(fchModiUpdate)*1000));
			objetoUpdate.setIdEstCat(Integer.parseInt(idEstCatUpdate));
			objetoUpdate.setIdCat(Integer.parseInt(id));
			serviceCata.updateRecord(objetoUpdate);
			response.getWriter().println("Objeto actualizado en BD");
			break;
		case "delete":
			String idDelete = request.getParameter("id");
			if(idDelete == null){
				//TODO
			}
			serviceCata.delete(Integer.parseInt(idDelete));
			response.getWriter().println("Objeto borrado en BD");
			break;
		default:
			List<CataDTO> list = serviceCata.findAllDTO_1();
			if (list == null) {
				response.getWriter().write("No data");
			} else {
				for (CataDTO dto : list) {
					response.getWriter().println("Catalogo: " + dto.getDscCorCat());
				}
			}
			break;
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
