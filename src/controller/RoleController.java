package controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Role;

@Named
@ViewScoped
public class RoleController extends Controller<Role> implements Serializable{

	private static final long serialVersionUID = -2128767325055983632L;

	@Override
	public Role getEntity() {
		if (entity == null)
			entity = new Role();
			
		return entity;
	}

}
