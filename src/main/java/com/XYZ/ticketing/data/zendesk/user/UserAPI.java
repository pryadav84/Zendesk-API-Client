package com.XYZ.ticketing.data.zendesk.user;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.WebResource;

public class UserAPI {

	private final WebResource rootResource;

	public UserAPI(WebResource rootResource) {

		super();
		this.rootResource = rootResource;

	}

	public UserWrapper getCurrentUser() {

		return rootResource.path("/api/v2/users/me.json").accept(MediaType.APPLICATION_JSON_TYPE).get(UserWrapper.class);
	}

	public UserWrapper getUser(int id) {

		return rootResource.path("/api/v2/users/" + id + ".json").accept(MediaType.APPLICATION_JSON_TYPE).get(UserWrapper.class);
	}

	public UserWrapper addUser(UserWrapper userWrapper) {

		return rootResource.path("/api/v2/users.json").accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(UserWrapper.class, userWrapper);

	}

	public UserWrapper deleteUser(int userId) {

		return rootResource.path("/api/v2/users/" + Integer.toString(userId) + ".json").accept(MediaType.APPLICATION_JSON_TYPE).delete(UserWrapper.class);
	}

	public UserCollection queryUser(String query) {

		return rootResource.path("/api/v2/users/search.json").queryParam("query", query).accept(MediaType.APPLICATION_JSON_TYPE).get(UserCollection.class);
	}
	
}


