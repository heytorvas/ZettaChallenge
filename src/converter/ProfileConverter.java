package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.Profile;
import repository.ProfileRepository;

@FacesConverter(value = "ProfileConverter")
public class ProfileConverter implements Converter<Profile> {
	
  @Override
  public Profile getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty())
			return null;
		ProfileRepository repo = new ProfileRepository();
		return repo.findById(Integer.valueOf(value));
  }

	@Override
	public String getAsString(FacesContext context, UIComponent component, Profile value) {
		if (value == null || value.getId() == null)
			return null;
		
		return value.getId().toString();
	}
}
