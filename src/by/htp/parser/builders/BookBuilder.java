package by.htp.parser.builders;

import by.htp.parser.bean.Book;
import by.htp.parser.bean.Entity;
import by.htp.parser.tags.MyTags;

public class BookBuilder extends EntityBuilder {

	private Book book;
	
	public BookBuilder(Entity entity) {
		super(entity);
		book = (Book) entity;
	}
	
	@Override
	public Entity buildEntity(MyTags tags, StringBuilder text) {
		if(book.getAuthor()==null || book.getId()==0 || book.getTitle()==null || book.getPublishedYear()==0) {
			setParametr(tags,text);
		}
		else {
			return book;
		}
		
		return null;
	}

	@Override
	public void setParametr(MyTags tags, StringBuilder text) {
		switch(tags){
			case AUTHOR:{
				book.setAuthor(text.toString().trim());
				break;
			}
			case PUBLISHEDYEAR:{
				book.setPublishedYear(Integer.parseInt(text.toString().trim()));
				break;
			}
			case TITLE:{
				book.setTitle(text.toString().trim());
				break;
			}
			default:{
				break;
			}
		}
	}

}
