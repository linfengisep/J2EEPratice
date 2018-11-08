package linfeng.beans;

public class User {
	private String firstName;
	private String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) throws BeanException {
		if(firstName.length()>10) {
			throw new BeanException("first name is too long, please make it less than 10 caracters.");
		}else {
			this.firstName = firstName;
		}
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
