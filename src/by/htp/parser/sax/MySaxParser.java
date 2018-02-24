package by.htp.parser.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.parser.bean.Book;
import by.htp.parser.bean.Employee;
import by.htp.parser.bean.Entity;
import by.htp.parser.builders.BookBuilder;
import by.htp.parser.builders.EmployeeBuilder;
import by.htp.parser.builders.EntityBuilder;
import by.htp.parser.factory.EntityFactory;
import by.htp.parser.tags.MyTags;

public class MySaxParser extends DefaultHandler {
	private StringBuilder text ;
	private List<Book> books;
	private List<Employee> employees;
	private Entity curEntity;
	private	EntityBuilder builder;
	
	public MySaxParser() {
		super();
		this.books = new ArrayList<>();
		this.employees = new ArrayList<>();
	}
	
	public MySaxParser(List<Book> books, List<Employee> employees) {
		super();
		this.books = books;
		this.employees = employees;
	}

	public List<Book> getBooksList(){
		if(books!=null)
		{
			return books;
		}
		else
			return null;
	}
	
	public List<Employee> getEmployeesList(){
		if(employees!=null)
		{
			return employees;
		}
		else
			return null;
	}
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start parse xml...");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		text = new StringBuilder();
		MyTags tags = MyTags.valueOf(qName.toUpperCase().replace("-","_")) ;
		EntityFactory factory = new EntityFactory();
		switch(tags) {
			case BOOKS:{
				books = new ArrayList<Book>();
				break;
			}
			case EMPLOYEES:{
				employees = new ArrayList<Employee>();
				break;
			}
			default:
				curEntity=factory.createEntity(tags,curEntity);
				break;
			}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		text.append(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		MyTags tags = MyTags.valueOf(qName.toUpperCase().replace("-","_")) ;
		switch(tags) {
			case ID:{
				curEntity.setId(Integer.parseInt(text.toString().trim()));
				break;
			}
			case BOOK:{
				books.add((Book)curEntity);
				curEntity=null;
				break;
			}
			case EMPLOYEE:{
				employees.add((Employee)curEntity);
				curEntity=null;
				break;
			}
			default:{
				if(curEntity instanceof Book) {
					builder = new BookBuilder(curEntity);
				}
				else if(curEntity instanceof Employee) {
					builder = new EmployeeBuilder(curEntity);
				}
				builder.buildEntity(tags, text);
				break;
			}
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("END parse xml...");
		super.endDocument();
	}
}
