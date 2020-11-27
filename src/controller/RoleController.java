package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Role;
import repository.RoleRepository;
import util.Util;

@Named
@ViewScoped
public class RoleController extends Controller<Role> implements Serializable {

	private static final long serialVersionUID = -2128767325055983632L;
	
	private List<Role> roleList;

	@Override
	public Role getEntity() {
		if (entity == null)
			entity = new Role();
			
		return entity;
	}
	
	@Override
	public void save() {
		RoleRepository rr = new RoleRepository();
		if(rr.containsName(getEntity().getName())) {
			Util.addMessageError("Cargo já cadastrado.");
		}
		else {
			super.save();
			Util.redirect("cargo.xhtml");
		}
	}
	
	@Override
	public void delete() {
		super.delete();
		Util.redirect("cargo.xhtml");
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

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
}
