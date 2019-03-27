package com.learn.trackzilla.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ticket")
public class Ticket{
	
	private int id;
	private String description;
	private int applicationId;
	private String status;
	
	public Ticket(){
		
	}

	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	public int getApplicationId() {
		return applicationId;
	}
	@XmlElement
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public String getStatus() {
		return status;
	}
	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}

	public Ticket(int id, String description, int applicationId, String status) {
		super();
		this.id = id;
		this.description = description;
		this.applicationId = applicationId;
		this.status = status;
	}
	
}

