package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import model.Profile;
import repository.ProfileRepository;
import util.Util;

@Named
@ViewScoped
public class ProfileController extends Controller<Profile> implements Serializable {

	private static final long serialVersionUID = -2508726304168348443L;
	
	private List<Profile> profileList;
	
	@Override
	public Profile getEntity() {
		if (entity == null)
			entity = new Profile();
			
		return entity;
	}
	
	@Override
	public void save() {
		ProfileRepository pr = new ProfileRepository();
		if(pr.containsName(getEntity().getName())) {
			Util.addMessageError("Perfil já cadastrado.");
		}
		else {
			super.save();
			Util.redirect("perfil.xhtml");
		}
	}
	
	@Override
	public void delete() {
		super.delete();
		Util.redirect("perfil.xhtml");
	}

	public List<Profile> getProfileList() {
		if (profileList == null) {
			ProfileRepository pr = new ProfileRepository();
			profileList = pr.findAll();
			if (profileList == null) {
				profileList = new ArrayList<Profile>();
			}
		}
		return profileList;
	}

	public void setProfileList(List<Profile> profileList) {
		this.profileList = profileList;
	}
}
