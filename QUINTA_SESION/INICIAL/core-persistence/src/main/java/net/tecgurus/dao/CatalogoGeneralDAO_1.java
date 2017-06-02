package net.tecgurus.dao;

import java.util.List;

import javax.ejb.Local;

import net.tecgurus.entities.CatalogoGeneral;

@Local
public interface CatalogoGeneralDAO_1 {

	List<CatalogoGeneral> findAllByQuery();

	List<CatalogoGeneral> findAllByTypedQuery();

	List<CatalogoGeneral> findAllByNamedQuery();

	List<CatalogoGeneral> findAllByNativeQuery();

	List<CatalogoGeneral> findByDscComCat(String param);

	void createCatalogoGeneral(CatalogoGeneral c);

	CatalogoGeneral findById(Integer id);

	void updateCatalogoGeneral(CatalogoGeneral c);

	void removeCatalogoGeneral(CatalogoGeneral c);

}
