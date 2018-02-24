package by.htp.parser.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import by.htp.parser.bean.Book;
import by.htp.parser.bean.Employee;

public class MyDomParser {
	private List<Book> books;
	private List<Employee> employees;
	private DOMParser parser;
	
	public MyDomParser() {
		parser= new DOMParser();
	}
	
	public void buildEntities(String name) throws SAXException, IOException {
		parser.parse(name);
		books = new ArrayList<Book>();
		employees = new ArrayList<Employee>();
		Document doc = parser.getDocument();
		Element root = doc.getDocumentElement();
		buildBooksList(root);
		buildEmployeesList(root);
	}
	
	private void buildBooksList(Element root) {
		NodeList bookNodes = root.getElementsByTagName("BOOKS");
		Book book = null;
		for ( int i = 0 ; i < bookNodes.getLength() ; ++i) {
			book = new Book();
			initBook(book, bookNodes, i);
			books.add(book);
		}
	}
	
	private void initBook(Book book,NodeList bookNodes, int itemInd) {
		Element bookElement = (Element ) bookNodes.item(itemInd);
		book.setId(Integer.parseInt(bookElement.getAttribute("ID")));
		book.setAuthor(getSingleChild(bookElement,"AUTHOR").getTextContent().trim());
		book.setTitle(getSingleChild(bookElement,"TITLE").getTextContent().trim());
		book.setPublishedYear(Integer.parseInt(getSingleChild(bookElement,"PUBLISHEDYEAR").getTextContent().trim()));
	}
	
	private void buildEmployeesList(Element root) {
		NodeList employeeNodes = root.getElementsByTagName("BOOKS");
		Employee employee = null;
		for ( int i = 0 ; i < employeeNodes.getLength() ; ++i) {
			employee = new Employee();
			initBook(employee, employeeNodes, i);
			employees.add(employee);
		}
	}
	
	private void initBook(Employee empl,NodeList employeeNodes, int itemInd) {
		Element employeeElement = (Element ) employeeNodes.item(itemInd);
		empl.setId(Integer.parseInt(employeeElement.getAttribute("ID")));
		empl.setName(getSingleChild(employeeElement,"NAME").getTextContent().trim());
		empl.setSurname(getSingleChild(employeeElement,"SURNAME").getTextContent().trim());
		empl.setEmail(getSingleChild(employeeElement,"EMAIL").getTextContent().trim());
		empl.setBirthday(getSingleChild(employeeElement,"BIRTHDAY").getTextContent().trim());
		int id = Integer.parseInt(employeeElement.getAttribute("READBOOKID") );
		while(  id  > 0 ) {
			empl.addReadBookId(id);
		}
	}
	
	private Element getSingleChild(Element el, String childName) {
		NodeList nList = el.getElementsByTagName(childName);
		return (Element) nList.item(0);
	}
}
