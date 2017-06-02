/*
 * Teknei 2014 - All rights reserved
 */
package net.tecgurus.persistence.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.SortOrder;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;

import net.tecgurus.common.exceptions.PersistenceException;
import net.tecgurus.persistence.QueryParameter;
import net.tecgurus.persistence.dao.GenericDAO;


/**
 * Session Bean implementation class GenericDAOBean
 * 
 * @author Jorge Amaro
 * @version 1.1
 * @since 1.0
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class GenericDAOBean<T> implements GenericDAO<T> {

	/*
	 * Injected value
	 */
	@PersistenceContext
	EntityManager em;

	/*
	 * Reference to persistent class 
	 */
	private Class<T> entityClass;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teknei.sitm.core.persistence.dao.GenericDAO#getResultList(int,
	 * int, java.lang.String, javax.swing.SortOrder, java.util.Map)
	 */
	@Override
	public List<T> getResultList(int first, int pageSize,
			Map<String, SortOrder> orders, Map<String, Object> filters)
			throws PersistenceException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> cquery = cb.createQuery(getEntityClass());
			Root<T> root = cquery.from(getEntityClass());
			cquery.select(root);
			List<Predicate> clist = getFilterConditions(root, cb, filters);
			if (!CollectionUtils.isEmpty(clist)) {
				cquery.where(cb.and(clist.toArray(new Predicate[0])));
			}
			List<Order> listOrders = formatOrders(orders, cb, root);
			if (CollectionUtils.isNotEmpty(listOrders)) {
				cquery.orderBy(listOrders);
			}
			TypedQuery<T> tquery = em.createQuery(cquery).setFirstResult(first)
					.setMaxResults(pageSize);
			List<T> list = tquery.getResultList();
			return list;
		} catch (Exception e) {
			throw new PersistenceException("Error in getResultList: "
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.sitm.core.persistence.dao.GenericDAO#formatOrders(java.util
	 * .Map, javax.persistence.criteria.CriteriaBuilder,
	 * javax.persistence.criteria.Root)
	 */
	@Override
	public List<Order> formatOrders(Map<String, SortOrder> params,
			CriteriaBuilder cb, Root<T> root) {
		if (params != null && !params.isEmpty()) {
			List<Order> lOrders = new LinkedList<Order>();
			for (String sortField : params.keySet()) {
				SortOrder sortOrder = params.get(sortField);
				if (sortOrder != null && sortOrder.equals(SortOrder.DESCENDING)) {
					Order fOrder = null;
					if (sortField.contains(".")) {
						String[] orderSplit = sortField.split("\\.");
						fOrder = cb.desc(root.join(orderSplit[0].trim()).get(
								orderSplit[1].trim()));
					} else {
						fOrder = cb.desc(root.get(sortField.trim()));
					}

					lOrders.add(fOrder);
				} else {
					Order fOrder = null;
					if (sortField.contains(".")) {
						String[] orderSplit = sortField.split("\\.");
						fOrder = cb.asc(root.join(orderSplit[0].trim()).get(
								orderSplit[1].trim()));
					} else {
						fOrder = cb.asc(root.get(sortField.trim()));
					}

					lOrders.add(fOrder);
				}
			}
			return lOrders;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teknei.sitm.core.persistence.dao.GenericDAO#count(java.util.Map)
	 */
	@Override
	public int count(Map<String, Object> filters) throws PersistenceException {
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Long> cqCount = cb.createQuery(Long.class);
			Root<T> root = cqCount.from(getEntityClass());
			List<Predicate> clist = getFilterConditions(root, cb, filters);
			cqCount.select(cb.count(root));
			cqCount.where(cb.and(clist.toArray(new Predicate[0])));
			return em.createQuery(cqCount).getSingleResult().intValue();
		} catch (Exception e) {
			throw new PersistenceException("Error in count:" + e.getMessage(),
					e);
		}
	}

	/**
	 * Formats the filters in required Criteria way
	 * 
	 * @param root
	 *            the entity root
	 * @param cb
	 *            the criteria builder instance
	 * @param filters
	 *            all the filters required
	 * @return a list of <code>Predicate</list> objects ready to be used
	 */
	private List<Predicate> getFilterConditions(Root<T> root,
			CriteriaBuilder cb, Map<String, Object> filters) {
		List<Predicate> clist = new LinkedList<Predicate>();
		if (filters != null) {
			for (Map.Entry<String, Object> filter : filters.entrySet()) {
				if (filter.getValue() instanceof String) {
					Path<String> path = null;
					if (filter.getKey().contains(".")) {
						String[] orderSplit = filter.getKey().split("\\.");
						path = root.join(orderSplit[0].trim()).get(
								orderSplit[1].trim());
					} else {
						path = root.get(filter.getKey().trim());

					}
					Predicate prd = cb.like(path, "%"
							+ filter.getValue().toString().trim() + "%");
					clist.add(prd);
				} else {
					Predicate prd = null;
					if (filter.getKey().contains(".")) {
						String[] orderSplit = filter.getKey().split("\\.");
						prd = cb.equal(
								root.join(orderSplit[0].trim()).get(
										orderSplit[1].trim()),
								filter.getValue());
					} else {
						prd = cb.equal(root.get(filter.getKey().trim()),
								filter.getValue());
					}
					clist.add(prd);
				}
			}
		}
		return clist;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOBean() {
		super();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.acabus.core.persistence.dao.GenericDAO#create(java.lang.Object
	 * )
	 */
	@Override
	public T create(T t) throws PersistenceException {
		try {
			this.em.detach(t);
			this.em.persist(t);
			this.em.flush();
			this.em.refresh(t);
			return t;
		} catch (Exception e) {
			throw new PersistenceException("Error in create:" + e.getMessage(),
					e);
		}

	}
	
	@Override
	public void detach(T  t) throws PersistenceException{
		try {
			this.em.detach(t);
		} catch (Exception e) {
			throw new PersistenceException("Error in create:" + e.getMessage(),
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.acabus.core.persistence.dao.GenericDAO#find(java.lang.Class,
	 * java.lang.Object)
	 */
	@Override
	public T find(Class<T> type, Object id) throws PersistenceException {
		try {
			return (T) this.em.find(type, id);
		} catch (Exception e) {
			throw new PersistenceException("Error in find:" + e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.acabus.core.persistence.dao.GenericDAO#delete(java.lang.Class,
	 * java.lang.Object)
	 */
	@Override
	public void delete(Class<T> type, Object id) throws PersistenceException {
		try {
			Object ref = this.em.getReference(type, id);
			this.em.remove(ref);
		} catch (Exception e) {
			throw new PersistenceException("Error in delete:" + e.getMessage(),
					e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.acabus.core.persistence.dao.GenericDAO#delete(java.lang.Object
	 * )
	 */
	@Override
	public void delete(T entity) throws PersistenceException {
		try {
			entity = em.merge(entity);
			this.em.remove(entity);
			em.flush();
		} catch (Exception e) {
			throw new PersistenceException("Error in delete:" + e.getMessage(),
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.acabus.core.persistence.dao.GenericDAO#update(java.lang.Object
	 * )
	 */
	@Override
	public T update(T t) throws PersistenceException {
		try {
			return (T) this.em.merge(t);
		} catch (Exception e) {
			throw new PersistenceException("Error in update:" + e.getMessage(),
					e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.acabus.core.persistence.dao.GenericDAO#findWithNamedQuery(
	 * java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findWithNamedQuery(String namedQueryName)
			throws PersistenceException {
		try {
			return this.em.createNamedQuery(namedQueryName).getResultList();
		} catch (Exception e) {
			throw new PersistenceException("Error in findWithNamedQuery:"
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teknei.acabus.core.persistence.dao.GenericDAO#findByNativeQuery(java
	 * .lang.String, java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByNativeQuery(String sql, Class<T> type)
			throws PersistenceException {
		try {
			return this.em.createNativeQuery(sql, type).getResultList();
		} catch (Exception e) {
			throw new PersistenceException("Error in findByNativeQuery:"
					+ e.getMessage(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teknei.acabus.core.persistence.dao.GenericDAO#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() throws PersistenceException {
		try {
			Query q = getEm().createQuery(
					"SELECT e FROM " + entityClass.getName() + " e");
			List<T> list = (List<T>) q.getResultList();
			return list;
		} catch (Exception e) {
			throw new PersistenceException(
					"Error in findAll:" + e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.teknei.sitm.core.persistence.dao.GenericDAO#findWithNamedQuery(java.lang.String, java.util.Map, int)
	 */
	@Override
	public List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int resultLimit)
			throws PersistenceException {
		try {
			Set<String> paramenterNames = parameters.keySet();
			TypedQuery<T> query = this.em.createNamedQuery(namedQueryName,
					entityClass);
			if (resultLimit > 0) {
				query.setMaxResults(resultLimit);
			}
			for (String paramName : paramenterNames) {
				query.setParameter(paramName, parameters.get(paramName));
			}
			return (List<T>) query.getResultList();
		} catch (Exception e) {
			throw new PersistenceException("Error in findWithNamedQuery:"
					+ e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.teknei.sitm.core.persistence.dao.GenericDAO#findWithNamedQuery(java.lang.String, java.util.Map, int, int)
	 */
	@Override
	public List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters, int firstResult, int resultLimit)
			throws PersistenceException {
		try {
			Set<String> paramenterNames = parameters.keySet();
			TypedQuery<T> query = this.em.createNamedQuery(namedQueryName,
					entityClass);
			if (resultLimit > 0) {
				query.setMaxResults(resultLimit);
				query.setFirstResult(firstResult);
			}
			for (String paramName : paramenterNames) {
				query.setParameter(paramName, parameters.get(paramName));
			}
			return (List<T>) query.getResultList();
		} catch (Exception e) {
			throw new PersistenceException("Error in findWithNamedQuery:"
					+ e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.teknei.sitm.core.persistence.dao.GenericDAO#findWithNamedQuery(java.lang.String, java.util.Map)
	 */
	@Override
	public List<T> findWithNamedQuery(String namedQueryName,
			Map<String, Object> parameters) throws PersistenceException {
		return findWithNamedQuery(namedQueryName, parameters, 0);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void saveList(List<T> list) throws PersistenceException {
		try {
			//FIX Hibernate implementation
			Session session = em.unwrap(Session.class);
			int batchSize = 100;
			for (int i = 0; i < list.size(); i++) {
				T entity = list.get(i);
				session.save(entity);
				if (i % batchSize == 0) {
					session.flush();
					session.clear();
				}
			}
			session.flush();
			session.clear();
		} catch (Exception e) {
			throw new PersistenceException("Error in saveList:" + e.getMessage(), e);
		}
	}


	/**
	 * @return the em
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * @param em
	 *            the em to set
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

	/**
	 * @return the entityClass
	 */
	public Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass
	 *            the entityClass to set
	 */
	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

}
