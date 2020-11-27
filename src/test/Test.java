package test;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;
import javax.persistence.EntityManager;

import controller.RoleController;
import factory.JPAFactory;
import model.Role;

public class Test {
	
	public static Object getAsObject() {
		List<Role> roleList = new ArrayList<Role>();
		String value = "1";
		
		Role r1 = new Role();
		r1.setId(1);
		r1.setName("gerente");
		roleList.add(r1);
		
		Role r2 = new Role();
		r2.setId(2);
		r2.setName("ceo");
		roleList.add(r2);
		
		if(!(value.equals("null")) && value.trim().length() > 0) {
			try {
				Integer stringConverted = Integer.parseInt(value);
				
				for (int i = 0; i < roleList.size(); i++) {
					if (roleList.get(i).getId().equals(stringConverted)) {
						Integer aux = roleList.indexOf(roleList.get(i));
						return roleList.get(aux);					
					}
				}
				
//				for (Role aux2 : roleList) {
//					if(aux2.getId().equals(aux)) {
//						Integer aux3 = roleList.indexOf(aux2);
//						System.out.println(roleList.get(aux3));
//						return roleList.get(aux3);
//					}
//				}
			} catch(NumberFormatException e) {
               throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}
		}
		
		return null;
	}
	
	public static void main(String[] args) {

		//System.out.println(getAsObject());
	}
}
