package com.admin.notemanager.bean;

public class Note {
	// properties
	private int id;
	private String title;
	private String category;
	private String description;
	private String dateCreated;
	
	// Parameterized constructor without id
	public Note(String title, String category, String description) {
		super();
		this.title = title;
		this.category = category;
		this.description = description;
	}
	
	// Parameterized constructor without date created
	public Note(int id, String title, String category, String description) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.description = description;
	}

	// Parameterized constructor
	public Note(int id, String title, String category, String description, String dateCreated) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.description = description;
		this.dateCreated = dateCreated;
	}

	// Getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
}
