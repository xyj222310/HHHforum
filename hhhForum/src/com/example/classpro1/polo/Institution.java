package com.example.classpro1.polo;


public class Institution {

	private String university;
	private String school;
	private String major;
	
	public Institution(String university, String school, String major) {
		super();
		this.university = university;
		this.school = school;
		this.major = major;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	
}
