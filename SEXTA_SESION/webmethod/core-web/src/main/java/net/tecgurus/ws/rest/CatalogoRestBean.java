package net.tecgurus.ws.rest;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;

import net.tecgurus.business.CatalogoBusiness;
import net.tecgurus.common.dto.CataDTO;

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
    
    @POST
    @Path("/alta")
    @Consumes("application/json")
    public String altaCatalogo(String catalogoJSON){
    	 JSONObject json = new JSONObject(catalogoJSON);
         String desc1 = json.optString("desc1", "fallback");
         String desc2 = json.optString("desc2", "fallback2");
         CataDTO dto = new CataDTO();
         dto.setDscCorCat(desc1);
         dto.setDscComCat(desc2);
         dto.setIdEstCat(1);
         dto.setFchCre(new Date(System.currentTimeMillis()));
         businessCatalogo.createNewRecord(dto);
         return "OK";
    }
    
}
