package by.htp.parser.builders;

import by.htp.parser.bean.Employee;
import by.htp.parser.bean.Entity;
import by.htp.parser.tags.MyTags;

public class EmployeeBuilder extends EntityBuilder {

	private Employee employee;
	
	public EmployeeBuilder(Entity entity) {
		super(entity);
		employee = (Employee) entity;
	}
	
	@Override
	public Employee buildEntity(MyTags tags, StringBuilder text) {
		if(		employee.getBirthday()==null || employee.getId()==0 || 
				employee.getName()==null || employee.getSurname()==null ||
				employee.getEmail()==null ) {
			setParametr(tags,text);
		}
		else if(tags.toString().equals("READBOOKID")) {
			setParametr(tags,text);
		}
		else {
			return employee;
		}
		
		return null;
	}

	@Override
	public void setParametr(MyTags tags, StringBuilder text) {
		switch(tags){
			case NAME:{
				employee.setName(text.toString().trim());
				break;
			}
			case EMAIL:{
				employee.setEmail(text.toString().trim());
				break;
			}
			case SURNAME:{
				employee.setSurname(text.toString().trim());
				break;
			}
			case BIRTHDAY:{
				employee.setBirthday(text.toString().trim().toUpperCase().replace("-","_"));
				break;
			}
			case READBOOKID:{
				employee.addReadBookId(Integer.parseInt(text.toString().trim()));
				break;
			}
			default:{
				break;
			}
		}
	}
}
