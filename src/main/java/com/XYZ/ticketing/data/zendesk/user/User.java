package com.XYZ.ticketing.data.zendesk.user;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;

public class User {

	private int id;
	private String name;
	private String email;
	private boolean verified;
	private boolean active;

	@JsonProperty("id")
	public int getId() {

		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {

		this.id = id;
	}

	@JsonProperty("name")
	public String getName() {

		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {

		this.name = name;
	}

	@JsonProperty("email")
	public String getEmail() {

		return email;
	}

	@JsonSetter("email")
	public void setEmail(String email) {

		this.email = email;
	}

	@JsonProperty("verified")
	public boolean getVerified() {

		return verified;
	}

	@JsonSetter("verified")
	public void setVerified(boolean verified) {

		this.verified = verified;
	}

	public boolean getActive() {

		return active;
	}

	@JsonSetter("active")
	public void setActive(boolean active) {

		this.active = active;
	}

}
