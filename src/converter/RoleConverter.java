package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Role;
import repository.RoleRepository;

@FacesConverter(value = "RoleConverter", forClass = Role.class)
public class RoleConverter implements Converter<Role>{

	@Override
	public Role getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty())
			return null;
		RoleRepository repo = new RoleRepository();
		return repo.findById(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Role value) {
		if (value == null || value.getId() == null)
			return null;
		
		return value.getId().toString();
	}

}
