package net.tecgurus.business.impl;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import net.tecgurus.business.CatalogoBusiness;
import net.tecgurus.common.dto.CataDTO;
import net.tecgurus.dao.CatalogoGeneralDAO_1;
import net.tecgurus.entities.CatalogoGeneral;

/**
 * Session Bean implementation class CatalogoBusinessImpl
 */
@Stateless
public class CatalogoBusinessImpl implements CatalogoBusiness {

	@EJB
    private CatalogoGeneralDAO_1 dao;
	
	@Override
	public List<CataDTO> findAllDTO_1(){
		List<CatalogoGeneral> listEntities = dao.findAllByQuery();
		List<CataDTO> listDTO  = new LinkedList<>();
		for(CatalogoGeneral c : listEntities){
			CataDTO d = new CataDTO();
			d.setDscComCat(c.getDscComCat());
			d.setDscCorCat(c.getDscCorCat());
			d.setFchCre(c.getFchCre());
			d.setIdCat(c.getIdCat());
			d.setIdEstCat(c.getIdEstCat());
			listDTO.add(d);
		}
		return listDTO;
	}

	
	//Metodo para crear un nuevo registro en BD
	@Override
	public void createNewRecord(CataDTO dto){
		CatalogoGeneral recordBD = new CatalogoGeneral();
		recordBD.setDscComCat(dto.getDscComCat());
		recordBD.setDscCorCat(dto.getDscCorCat());
		recordBD.setFchCre(dto.getFchCre());
		recordBD.setFchMod(dto.getFchMod());
		recordBD.setIdEstCat(dto.getIdEstCat());
		dao.createCatalogoGeneral(recordBD);
	}
	
	//Metodo para actualizar un registro existente en BD
	@Override
	public void updateRecord(CataDTO dto){
		CatalogoGeneral bdRecord = dao.findById(dto.getIdCat());//Recuperar el objeto origen de la BD
		bdRecord.setDscComCat(dto.getDscComCat());
		bdRecord.setDscCorCat(dto.getDscCorCat());
		bdRecord.setFchCre(dto.getFchCre());
		bdRecord.setFchMod(dto.getFchMod());
		bdRecord.setIdEstCat(dto.getIdEstCat());
		//Update a nivel de BD
		dao.updateCatalogoGeneral(bdRecord);
	}
	
	//Metodo para el borrado
	@Override
	public void delete(Integer id){
		CatalogoGeneral c = dao.findById(id);
		dao.removeCatalogoGeneral(c);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
