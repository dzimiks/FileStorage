package model;

/**
 * Student model for loading data from json.
 */
public class Student {
	private String name;
	private String surname;
	private String index;
	private String group;
	private String implementation;
	private String accessToken;

	public Student(
			String name,
			String surname,
			String index,
			String group,
			String implemetation,
			String accessToken) {
		this.name = name;
		this.surname = surname;
		this.index = index;
		this.group = group;
		this.implementation = implemetation;
		this.accessToken = accessToken;
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

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
