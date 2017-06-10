package net.tecgurus.ws.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import net.tecgurus.business.CatalogoBusiness;
import net.tecgurus.catservice._1.*;
import net.tecgurus.common.dto.CataDTO;

@WebService(serviceName = "catService", 
endpointInterface = "net.tecgurus.catservice._1.CatServicePortType", 
targetNamespace = "http://www.tecgurus.net/catService/1.0")
public class CatWSImpl implements CatServicePortType {

	@EJB
	private CatalogoBusiness business;
	
	@Override
	public CatGralResponse listarCatalogoOperation(CatGralRequest catGralRequest) {
		CatGralResponse response = new CatGralResponse();
		StatusResponse st = new StatusResponse();
		st.setStatus("00");
		response.setStatus(st);
	 	List<CataDTO> list = business.findAllDTO_1();
	 	List<CatGralData> listData = new ArrayList<>();
		for(CataDTO d : list){
			CatGralData data = new CatGralData();
			data.setDescComp(d.getDscComCat());
			data.setDescCort(d.getDscCorCat());
			data.setIdCat(d.getIdCat());
			data.setIdEstCat(d.getIdEstCat());
			listData.add(data);
		}
		response.getCatGralInfo().addAll(listData);
		return response;
	}

	@Override
	public CatGralAltaResponse altaCatalogoOperation(CatGralAltaRequest catGralAltaRequest) {
		//1 Obtener datos de entrada para llenar el DTO
		String descCort = catGralAltaRequest.getCataData().getDescCort();
		String descComp = catGralAltaRequest.getCataData().getDescComp();
		//2 crear el DTO
		CataDTO dto = new CataDTO();
		dto.setDscComCat(descComp);
		dto.setDscCorCat(descCort);
		dto.setIdEstCat(1);
		dto.setFchCre(new Date(System.currentTimeMillis()));
		//3 guardarlo en la BD
		business.createNewRecord(dto);
		StatusResponse st = new StatusResponse();
		st.setStatus("00");
		CatGralAltaResponse response = new CatGralAltaResponse();
		response.setStatus(st);
		return response;
	}

	@Override
	public CatGralModiResponse modificacionCatalogoOperation(CatGralModiRequest catGralModiRequest) {
		StatusResponse st = new StatusResponse();
		st.setStatus("00");
		CatGralModiResponse response = new CatGralModiResponse();
		response.setStatus(st);
		return response;
	}

	@Override
	public CatGralBajaResponse bajaCatalogoOperatioon(CatGralBajaRequest catGralBajaRequest) {
		CatGralBajaResponse response = new CatGralBajaResponse();
		BajaDatosResponseDummy dummy = new BajaDatosResponseDummy();
		dummy.setOutput("HOLA MUNDO");
		response.setResponseDummy(dummy);
		return response;
	}


	
}
