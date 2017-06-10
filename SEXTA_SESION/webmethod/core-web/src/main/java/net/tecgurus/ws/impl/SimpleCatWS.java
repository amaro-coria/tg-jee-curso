package net.tecgurus.ws.impl;

import net.tecgurus.business.CatalogoBusiness;
import net.tecgurus.common.dto.CataDTO;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Amaro on 10/06/2017.
 */
@WebService
public class SimpleCatWS {

    @EJB
    private CatalogoBusiness businessRef;

    @WebMethod
    public CataDTO findDummy(){
        CataDTO dto = new CataDTO();
        dto.setDscCorCat("DUMMY");
        dto.setDscComCat("DUMMY_2");
        return dto;
    }

    @WebMethod
    public List<CataDTO> findAll(){
        return businessRef.findAllDTO_1();
    }

}
