package com.XYZ.ticketing.data.zendesk.user;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

import com.XYZ.ticketing.data.zendesk.APIFactory;

public class UserAPITest extends TestCase {

	public void testGetCurrentUser() throws FileNotFoundException, IOException {

		UserAPI userapi = new APIFactory().getUserAPI();
		assertNotNull(userapi);

		UserWrapper userWrapper = userapi.getCurrentUser();
		assertNotNull(userWrapper);
		assertNotNull(userWrapper.getUser());
		assertTrue(userWrapper.getUser().getId() > 0);
	}

}
