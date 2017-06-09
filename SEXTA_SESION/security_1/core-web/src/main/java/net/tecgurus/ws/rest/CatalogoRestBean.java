package net.tecgurus.ws.rest;

import net.tecgurus.business.CatalogoBusiness;
import net.tecgurus.common.dto.CataDTO;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Amaro on 02/06/2017.
 */
@Stateless
@LocalBean
@Path("/catalogo/")
public class CatalogoRestBean {

    @EJB
    private CatalogoBusiness businessCatalogo;

    @POST
    @Path("/findById/{id}")
    @Produces("application/json" )
    public CataDTO findById(@PathParam("id") int id) {
        List<CataDTO> list = businessCatalogo.findAllDTO_1();
        for (CataDTO d : list) {
            if (d.getIdCat() == id) {
                return d;
            }
        }
        return null;
    }
}
