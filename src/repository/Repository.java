package repository;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.validation.ValidationException;

import factory.JPAFactory;
import model.DefaultEntity;
import util.ApplicationException;

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
			throw new ApplicationException("Problema ao iniciar uma transação.");
		}
	}
	
	public void commitTransaction() throws ApplicationException {
		try {
			getEntityManager().getTransaction().commit();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Problema ao fazer commit de uma transação");
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
			throw new ApplicationException("Vers�o antiga. Erro de controle de concorr�ncia.");
		} catch (Exception e) {
			System.out.println("Erro no repositorio "
					+ "ao executar o método merge.");
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
			
			System.out.println("Erro no repositório ao executar o m�todo merge.");
			e.printStackTrace();
			throw new ApplicationException("Erro ao salvar.");
		}
	}
	
	public T findById(Integer id) {
		// obtendo o tipo da classe de forma generica (a classe deve ser publica)
		
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass(); 
		Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];
		
		// pesquisando pelo id no banco
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