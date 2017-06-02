package net.tecgurus.ws.impl;

import javax.jws.WebService;

import net.tecgurus.catservice._1.CatGralAltaRequest;
import net.tecgurus.catservice._1.CatGralAltaResponse;
import net.tecgurus.catservice._1.CatGralModiRequest;
import net.tecgurus.catservice._1.CatGralModiResponse;
import net.tecgurus.catservice._1.CatGralRequest;
import net.tecgurus.catservice._1.CatGralResponse;
import net.tecgurus.catservice._1.CatServicePortType;
import net.tecgurus.catservice._1.StatusResponse;

@WebService(serviceName = "catService", endpointInterface = "net.tecgurus.catservice._1.CatServicePortType", targetNamespace = "http://www.tecgurus.net/catService/1.0")
public class CatWSImpl implements CatServicePortType {

	@Override
	public CatGralResponse listarCatalogoOperation(CatGralRequest catGralRequest) {
		CatGralResponse response = new CatGralResponse();
		StatusResponse st = new StatusResponse();
		st.setStatus("00");
		response.setStatus(st);
		return response;
	}

	@Override
	public CatGralAltaResponse altaCatalogoOperation(CatGralAltaRequest catGralAltaRequest) {
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

}
