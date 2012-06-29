package com.XYZ.ticketing.data.zendesk.user;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class UserCollection {

	private int count;
	private List<User> users;

	public int getCount() {

		return count;
	}

	@JsonProperty("count")
	public void setCount(int count) {

		this.count = count;
	}

	public List<User> getUsers() {

		return users;
	}

	@JsonProperty("users")
	public void setUsers(List<User> users) {

		this.users = users;
	}

}
