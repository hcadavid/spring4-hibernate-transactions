/**
 * 
 */
package edu.eci.cosw.polizas.persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;


public class HibernateGenericDAO<T, PK extends Serializable> implements
		GenericDAO<T, PK> {

private Class<T> baseClass;
	
	private SessionFactory sessionf;
	
	public void setSession(SessionFactory sessionf) {
		this.sessionf = sessionf;
	}

	protected SessionFactory getSessionFactory(){
		return sessionf;
	}
	

	public HibernateGenericDAO(Class<T> baseClass) {
		super();
		this.baseClass=baseClass;				
	}

	
	
	public void save(T o) throws PersistenceException{
		try{
			sessionf.getCurrentSession().saveOrUpdate(o);
		}
		catch (HibernateException e){
			throw new PersistenceException("Error on saving object:"+o.toString(),e);
		}
	}


	public T load(PK id) throws PersistenceException{
		try{
			return (T)sessionf.getCurrentSession().load(baseClass, id);
		}
		catch(HibernateException e){
			throw new PersistenceException("Error on loading object:."+id.toString(),e);
		}
	}

	public void makeTransient(T o) throws PersistenceException{
		try{
			sessionf.getCurrentSession().delete(o);	
		}
		catch(HibernateException e){
			throw new PersistenceException("Error on making transient an object:."+o.toString(),e);
		}
			
	}
	
	
	public List<T> loadAll() throws PersistenceException{
		try{
			Criteria cr= sessionf.getCurrentSession().createCriteria(baseClass);						
			return cr.list();	
		}
		catch(HibernateException e){
			throw new PersistenceException("Error on loading all instances of:"+baseClass.getName(),e);
		}
	}
	

}
