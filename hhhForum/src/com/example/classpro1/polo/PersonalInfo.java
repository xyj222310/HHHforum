package com.example.classpro1.polo;

public class PersonalInfo {
	private String name;
	private String password;
	private String male;
	private String favorate;
	private String school;

	public PersonalInfo(String name, String password, String male) {
		super();
		this.name = name;
		this.male = male;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMale() {
		return male;
	}

	public void setMale(String male) {
		this.male = male;
	}

	public String getFavorate() {
		return favorate;
	}

	public void setFavorate(String favorate) {
		this.favorate = favorate;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}
