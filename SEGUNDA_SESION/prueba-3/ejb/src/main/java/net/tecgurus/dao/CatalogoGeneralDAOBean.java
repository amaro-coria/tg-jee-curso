package net.tecgurus.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import net.tecgurus.entidades.Catalogo_General;

@Stateless
public class CatalogoGeneralDAOBean implements CatalogoGeneraDAO{

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Catalogo_General> findAll(){
		String jpql = "From Catalogo_General";
		Query query = em.createQuery(jpql);
		List l = query.getResultList();
		return l;
	}
	

}
