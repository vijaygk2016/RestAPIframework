package com.qa.data;

public class Users {

	String name;
	String job;
	String createdAt;
	String id;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getcreatedAt() {
		return createdAt;
	}

	public void setcreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Users() {

	}

	public Users(String name, String job) {
		this.name=name;
		this.job=job;

	}

}
