package linfeng.beans;

public class User {
	private String name;
	private int age;
	private boolean working;
	private String domaine;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isWorking() {
		return working;
	}
	public void setWorking(boolean isWorking) {
		this.working = isWorking;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	
}
