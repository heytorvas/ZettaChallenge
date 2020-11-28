package repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.validation.ValidationException;

import factory.JPAFactory;
import model.DefaultEntity;
import util.ApplicationException;
import util.Util;

public class Repository<T extends DefaultEntity<? super T>> {
	
	private EntityManager entityManager;
	
	public Repository() {
		entityManager = JPAFactory.getEntityManager();
	}
	
	public Repository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void beginTransaction() throws ApplicationException {
		try {
			getEntityManager().getTransaction().begin();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Problema ao iniciar uma transacao.");
		}
	}
	
	public void commitTransaction() throws ApplicationException {
		try {
			getEntityManager().getTransaction().commit();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Problema ao fazer commit de uma transacao");
		}
	}
	
	public void rollbackTransaction() {
		try {
			getEntityManager().getTransaction().rollback();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public T save(T entity) throws ApplicationException, ValidationException {
		try {
			return getEntityManager().merge(entity);
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (OptimisticLockException e) {
			e.printStackTrace();
			throw new ApplicationException("Versão antiga. Erro de controle de concorrência.");
		} catch (Exception e) {
			System.out.println("Erro no repositorio "
					+ "ao executar o metodo merge.");
			e.printStackTrace();
			throw new ApplicationException("Erro ao salvar.");
		} 
	}
	
	public void delete(T entity) throws ApplicationException {
		try {
			T obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);	
		} 
		catch (Exception e) {
			System.out.println("Erro no repositorio ao executar o metodo merge.");
			e.printStackTrace();
			throw new ApplicationException("Erro ao salvar.");
		}
	}
	
	public T findById(Integer id) {
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass(); 
		Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];
		
		T t = (T) getEntityManager().find(theType, id);
		return t;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}