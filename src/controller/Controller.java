package controller;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.validation.ValidationException;

import factory.JPAFactory;
import model.DefaultEntity;
import repository.Repository;
import util.ApplicationException;
import util.Util;

public abstract class Controller<T extends DefaultEntity<? super T>> implements Serializable {

	private static final long serialVersionUID = 5864180948764640904L;
	protected T entity;

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Controller() {
		super();
	}

	public void save() {
		Repository<T> r = new Repository<T>();
		try {
			r.beginTransaction();
			setEntity(r.save(getEntity()));
			r.commitTransaction();
			clear();
		} catch (ApplicationException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Problema ao salvar.");
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			r.rollbackTransaction();
			Util.addMessageError(e.getMessage());
		}
	}

	public void delete() {
		Repository<T> r = new Repository<T>();
		try {
			r.beginTransaction();
			r.delete(getEntity());
			r.commitTransaction();
		} catch (ApplicationException e) {
			e.printStackTrace();
			r.rollbackTransaction();
			Util.addMessageError("Problema ao excluir.");
			return;
		}
		clear();
		Util.addMessageInfo("Exclusão realizada com sucesso.");
	}

	public void edit(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((T) em.find(getEntity().getClass(), id));
	}

	public void clear() {
		entity = null;
	}

}
