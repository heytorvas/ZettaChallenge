package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.validation.ValidationException;

import factory.JPAFactory;
import model.Customer;
import model.Gender;
import model.Profile;
import model.Role;
import repository.CustomerRepository;
import repository.Repository;
import repository.RoleRepository;
import util.ApplicationException;
import util.Util;

@Named
@ViewScoped
public class CustomerController extends Controller<Customer> implements Serializable {

	private static final long serialVersionUID = -5704802606603041555L;
	
	private List<Customer> customerList;
	private List<Role> roleList;
	private List<Profile> selectedProfiles;
	
	@Override
	public Customer getEntity() {
		if (entity == null) {
			entity = new Customer();
			entity.setRole(new Role());
			entity.setProfileList(new ArrayList<Profile>());
		}
			
		return entity;
	}
	
	@Override
	public void save() {
		CustomerRepository cr = new CustomerRepository();
		if(cr.containsCpf(getEntity().getCpf())) {
			Util.addMessageError("CPF já cadastrado.");
		}
		else {
			Repository<Customer> r = new Repository<Customer>();
			try {
				r.beginTransaction();
				getEntity().setProfileList(getSelectedProfiles());
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
			Util.redirect("usuario.xhtml");
		}
	}
	
	@Override
	public void delete() {
		super.delete();
		Util.redirect("usuario.xhtml");
	}
	
	@Override
	public void edit(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity((Customer) em.find(getEntity().getClass(), id));
		
		CustomerRepository cr = new CustomerRepository();
		List<Profile> getSelectProfilesDB = new ArrayList<Profile>();
		getSelectProfilesDB = cr.findAllProfileByCustomerId(getEntity().getId());
		
		setSelectedProfiles(getSelectProfilesDB);
	}
	

	public List<Customer> getCustomerList() {
		if (customerList == null) {
			CustomerRepository cr = new CustomerRepository();
			customerList = cr.findAll();
			
			List<Profile> getSelectProfilesDB = new ArrayList<Profile>();
			List<Customer> aux = new ArrayList<Customer>();
			
			for (Customer customer : customerList) {
				getSelectProfilesDB = cr.findAllProfileByCustomerId(customer.getId());
				customer.setProfileList(getSelectProfilesDB);
				aux.add(customer);
			}
			
			customerList = aux;
			
			if (customerList == null) {
				customerList = new ArrayList<Customer>();
			}
		}
		return customerList;
	}
	
	public List<Role> getRoleList() {
		if (roleList == null) {
			RoleRepository rr = new RoleRepository();
			roleList = rr.findAll();
			if (roleList == null) {
				roleList = new ArrayList<Role>();
			}
		}
		return roleList;
	}
	
	public Gender[] getGenderList() {
		return Gender.values();
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	
	public List<Profile> getSelectedProfiles() {
		if (selectedProfiles == null) {
			selectedProfiles = new ArrayList<Profile>();
		}
		return selectedProfiles;
	}
	public void setSelectedProfiles(List<Profile> selectedProfiles) {
		this.selectedProfiles = selectedProfiles;
	}

}
