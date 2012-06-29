package com.XYZ.ticketing.data.zendesk.user;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserWrapper {

	private User user;

	@JsonProperty("user")
	public User getUser()
	{
		return user;
	}
	
	@JsonProperty("user")
	public void setUser(User u)
	{
		this.user = u;
	}
	
}
