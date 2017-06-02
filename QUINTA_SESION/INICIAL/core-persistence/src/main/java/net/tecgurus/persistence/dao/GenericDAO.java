/*
 * Teknei 2014 - All rights reserved
 */
package net.tecgurus.persistence.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.swing.SortOrder;

import net.tecgurus.common.exceptions.PersistenceException;;

/**
 * Generic interface for DAO access
 * @author Jorge Amaro
 * @version 1.1
 * @since 1.0
 *
 * @param <T> Entity class
 */
public interface GenericDAO<T> {

	/**
	 * Creates a record
	 * @param t entity
	 * @return the Entity saved instance
	 * @throws PersistenceException
	 */
	public T create(T t) throws PersistenceException;

	/**
	 * Finds a record based on its Id
	 * @param type the Class type of the record
	 * @param id the id of the record
	 * @return the instance
	 * @throws PersistenceException
	 */
	public T find(Class<T> type, Object id) throws PersistenceException;

	/**
	 * Updates the record instance
	 * @param t the instance
	 * @return the instance updated
	 * @throws PersistenceException
	 */
	public T update(T t) throws PersistenceException;

	/**
	 * Delete an instance of the class based on its id
	 * @param type the class 
	 * @param id the id of the record
	 * @throws PersistenceException
	 */
	public void delete(Class<T>  type, Object id) throws PersistenceException;

	/**
	 * Executes a find based on a named query
	 * @param queryName
	 * @return
	 * @throws PersistenceException
	 */
	public List<T>  findWithNamedQuery(String queryName) throws PersistenceException;

	/**
	 * Executes a native query and return the list elements
	 * @param sql
	 * @param type
	 * @return
	 * @throws PersistenceException
	 */
	List<T> findByNativeQuery(String sql, Class<T> type) throws PersistenceException;

	/**
	 * Finds all elements 
	 * @return the list
	 * @throws PersistenceException
	 */
	List<T> findAll() throws PersistenceException;

	/**
	 * Deletes an instance
	 * @param entity the instance
	 * @throws PersistenceException
	 */
	void delete(T entity) throws PersistenceException;

	/**
	 * Finds entities with named query, parameters and result limit
	 * @param namedQueryName
	 * @param parameters
	 * @param resultLimit
	 * @throws PersistenceException
	 * @return
	 */
	List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int resultLimit) throws PersistenceException;

	/**
	 * Finds entities with named query and parameters and no result limit
	 * @param namedQueryName
	 * @param parameters
	 * @throws PersistenceException
	 * @return
	 */
	List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters) throws PersistenceException;

	/**
	 * Finder with parameters 
	 * @param namedQueryName
	 * @param parameters
	 * @param firstResult
	 * @param resultLimit
	 * @throws PersistenceException
	 * @return
	 */
	List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int firstResult, int resultLimit)  throws PersistenceException;

	/**
	 * Finds elements in lazy loading format
	 * @param first the first index of the list
	 * @param pageSize the last index of the list
	 * @param sortField if any, the sort field
	 * @param sortOrder if any, the sort order
	 * @param filters the filter parameters
	 * @return all matching records
	 * @throws PersistenceException
	 */
	List<T> getResultList(int first, int pageSize, Map<String, SortOrder> orders,  Map<String, Object> filters)
			throws PersistenceException;

	/**
	 * Counts the total of elements regardless if there is a lazy loading or not
	 * @param filters
	 * @return
	 * @throws PersistenceException
	 */
	int count(Map<String, Object> filters) throws PersistenceException;

	/**
	 * Format orders in API model required by JPA
	 * @param params the params to be applied
	 * @param cb the criteria builder
	 * @param root the root entity
	 * @return
	 */
	List<Order> formatOrders(Map<String, SortOrder> params, CriteriaBuilder cb,
			Root<T> root);

	/**
	 * Detach the given entity from the Persistence Context
	 * @param t
	 * @throws PersistenceException
	 */
	void detach(T t) throws PersistenceException;

	void saveList(List<T> list) throws PersistenceException;


}
