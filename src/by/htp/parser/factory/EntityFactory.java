package by.htp.parser.factory;

import by.htp.parser.bean.Book;
import by.htp.parser.bean.Employee;
import by.htp.parser.bean.Entity;
import by.htp.parser.tags.MyTags;

public class EntityFactory {
	private Entity entity;
	
	public Entity createEntity(MyTags tags,Entity entity) {
		if(entity == null) {
			switch(tags) {
				case EMPLOYEE:{
					entity = new Employee();
					break;
				}
				case BOOK:{
					entity =  new Book();
					break;
				}
			default:
				break;
			}
		}	
		this.entity = entity;
		return this.entity;
	}
}
