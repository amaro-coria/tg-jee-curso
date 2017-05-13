package net.tecgurus.dao;

import java.util.List;

import javax.ejb.Local;

import net.tecgurus.entidades.Catalogo_General;

@Local
public interface CatalogoGeneraDAO {

	List<Catalogo_General> findAll();

}
