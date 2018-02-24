package by.htp.parser.stax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.parser.bean.Book;
import by.htp.parser.bean.Employee;
import by.htp.parser.tags.MyTags;


public class MySTaxParser {
	private List<Book> books;
	private List<Employee> employees;
	private XMLInputFactory inputFactory;
	
	public MySTaxParser() {
		inputFactory = XMLInputFactory.newInstance();
		employees = new ArrayList<>();
		books = new ArrayList<>();
	}

	public List<Book> getBooksList() {
		return books;
	}

	public List<Employee> getEmployeesList() {
		return employees;
	}
	
	public void buildEnities (String fileName) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			while(reader.hasNext()) {
				int type = reader.next();
				if( type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if(name.toUpperCase().replace("-","_").equals(MyTags.BOOK.toString())) {
						Book b = buildBook(reader);
						books.add(b);
					}
					else if(name.toUpperCase().replace("-","_").equals(MyTags.EMPLOYEE.toString())) {
						Employee e = buildEmployee(reader);
						employees.add(e);
					}
				}
			}
		}
		catch(XMLStreamException ex) {
			ex.printStackTrace();
		}
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if( inputStream != null) {
					inputStream.close();
				}
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private Book buildBook(XMLStreamReader reader) throws NumberFormatException, XMLStreamException {
		Book book = new Book();
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
				case XMLStreamConstants.START_ELEMENT:{
					name = reader.getLocalName();
					setBookParametr(name,book,reader);
					break;
				}
				case  XMLStreamConstants.END_ELEMENT:{
					name = reader.getLocalName();
					if( MyTags.valueOf(name.toUpperCase())==MyTags.BOOK) {
						return book;
					}
					break;
				}
			}
		}
		return book;
	}
	
	private Employee buildEmployee(XMLStreamReader reader) throws XMLStreamException {
		Employee employee = new Employee();
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
				case XMLStreamConstants.START_ELEMENT:{
					name = reader.getLocalName();
					setEmployeeParametr(name,employee,reader);
					break;
				}
				case  XMLStreamConstants.END_ELEMENT:{
					name = reader.getLocalName();
					if( MyTags.valueOf(name.toUpperCase())==MyTags.EMPLOYEE) {
						return employee;
					}
					break;
				}
			}
		}
		
		return employee;
	}
	
	private void setBookParametr(String name, Book book, XMLStreamReader reader) throws XMLStreamException {
		MyTags tags = MyTags.valueOf(name.toUpperCase().trim());
		switch(tags){
			case ID:{
				book.setId(Integer.parseInt(getXmlText(reader)));
				break;										
			}
			case AUTHOR:{
				book.setAuthor(getXmlText(reader));
				break;
			}
			case PUBLISHEDYEAR:{
				book.setPublishedYear(Integer.parseInt(getXmlText(reader).trim()));
				break;
			}
			case TITLE:{
				book.setTitle(getXmlText(reader).trim());
				break;
			}
			default:{
				break;
			}
		}				
	}
	
	private void setEmployeeParametr(String name, Employee employee, XMLStreamReader reader) throws XMLStreamException {
		MyTags tags = MyTags.valueOf(name.toUpperCase().trim());
		switch(tags){
			case ID:{
				employee.setId(Integer.parseInt(getXmlText(reader)));
				break;										
				}
			case NAME:{
				employee.setName(getXmlText(reader));
				break;
				}
			case SURNAME:{
				employee.setSurname(getXmlText(reader)); 
				break;
				}
			case EMAIL:{
				employee.setEmail(getXmlText(reader).trim());
				break;
				}
			case BIRTHDAY:{
				employee.setBirthday(getXmlText(reader).trim().toUpperCase().replace("-","_"));
				break;
				}
			case READBOOKID:{
				employee.addReadBookId(Integer.parseInt(getXmlText(reader)));
				break;
				}
			default:{
				break;
				}
			}				
	}
	
	private String getXmlText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
			return text;
		}
		return "";
	}
}
