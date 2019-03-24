package com.learn.trackzilla;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="application")
public class Application {

	private int id;
	private String name;
	private String description;
	
	public Application() {
		
	}

	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
    
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
}
