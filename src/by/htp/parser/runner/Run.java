package by.htp.parser.runner;

import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.parser.bean.Book;
import by.htp.parser.bean.Employee;
import by.htp.parser.sax.MySaxParser;
import by.htp.parser.stax.MySTaxParser;

public class Run {
	public static void main(String[] args) throws SAXException, IOException {
		//XMLReader reader = XMLReaderFactory.createXMLReader();
		//MySaxParser parser = new MySaxParser();
	
		MySTaxParser parser = new MySTaxParser();
		parser.buildEnities("src\\resources\\xml\\library.xml");
		
		//reader.setContentHandler(parser);
		//reader.parse(new InputSource("src\\resources\\xml\\library.xml"));
	
		List<Book> fuckingBooks = parser.getBooksList();
		List<Employee> exhaustedEmployeees = parser.getEmployeesList();
		for(Book b: fuckingBooks) {
			System.out.println(b);
		}
		
		for(Employee e: exhaustedEmployeees) {
			System.out.println(e);
		}
	}
}
