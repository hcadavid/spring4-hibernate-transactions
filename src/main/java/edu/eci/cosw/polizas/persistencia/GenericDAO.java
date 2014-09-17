/**
 * 
 */
package edu.eci.cosw.polizas.persistencia;

import java.io.Serializable;
import java.util.List;


public interface GenericDAO<T,PK extends Serializable> {

	/**
	 * 
	 * @param o
	 * @throws PersistenceException
	 */
	public void save(T o) throws PersistenceException;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public T load(PK id) throws PersistenceException;
	/**
	 * 	
	 * @param o
	 * @throws PersistenceException
	 */
	public void makeTransient(T o) throws PersistenceException;
	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */
	public List<T> loadAll() throws PersistenceException;
}