package br.edu.ifpb.caju.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.ifpb.caju.model.Membro;

@FacesConverter(value="MembrosConverter", forClass=Membro.class)
public class MembroConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
    	return (Membro) SelectItemsUtils.findValueByStringConversion(context, component, value, this);    
    } 
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object membro) {
		String id = "0";
		if (membro != null) {
			
			id = ((Membro) membro).getId()+"";
		}
		return id;
		
	}

}
