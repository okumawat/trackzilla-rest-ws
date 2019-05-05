package com.learn.trackzilla.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="application")
public class Application {

	private int id;
	private String name;
	private String description;
	private Set<Ticket> tickets;
	
	public Set<Ticket> getTickets() {
		return tickets;
	}
	@XmlElement
	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Application(int id, String name, String description,Set<Ticket> tickets) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.tickets = tickets;
	}

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
