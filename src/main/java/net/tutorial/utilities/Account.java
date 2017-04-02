package net.tutorial.utilities;


public class Account {
	private String email;
	private String password;
	private String name;
	private int number;
	private String gender;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String email, String password, String name,
			int number, String gender) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.number = number;
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}