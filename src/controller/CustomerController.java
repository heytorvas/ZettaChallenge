package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Customer;
import model.Gender;
import model.Role;
import repository.CustomerRepository;
import repository.RoleRepository;
import util.Util;

@Named
@ViewScoped
public class CustomerController extends Controller<Customer> implements Serializable {

	private static final long serialVersionUID = -5704802606603041555L;
	
	private List<Customer> customerList;
	private List<Role> roleList;
	
	@Override
	public Customer getEntity() {
		if (entity == null) {
			entity = new Customer();
			entity.setRole(new Role());
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
			super.save();
			Util.redirect("usuario.xhtml");
		}
	}
	
	@Override
	public void delete() {
		super.delete();
		Util.redirect("usuario.xhtml");
	}
	

	public List<Customer> getCustomerList() {
		if (customerList == null) {
			CustomerRepository cr = new CustomerRepository();
			customerList = cr.findAll();
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

}
