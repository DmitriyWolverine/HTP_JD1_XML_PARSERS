package by.htp.parser.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee extends Entity{
	private String name;
	private String surname;
	private String email;
	private Date birthday;
	private List<Integer> readBooksId;
	
	public Employee() {
		super();
		readBooksId = new ArrayList<>();
	}
	
	public Employee(int id) {
		super(id);
		readBooksId = new ArrayList<>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy_MM_dd");
		try {
			this.birthday = dateFormat.parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> getReadBooksId() {
		return readBooksId;
	}

	public void setReadBooksId(List<Integer> readBooksId) {
		this.readBooksId = readBooksId;
	}
	
	public void addReadBookId(Integer newId) {
		if(newId!=null && newId>=1)
			readBooksId.add(newId);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((readBooksId == null) ? 0 : readBooksId.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (readBooksId == null) {
			if (other.readBooksId != null)
				return false;
		} else if (!readBooksId.equals(other.readBooksId))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + super.getId() + ", name=" + name + ", surname=" + surname + ", email=" + email + ", birthday="
				+ birthday + ", readBooksId=" + readBooksId + "]";
	}

}
