package com.generation.opticvbeckend.automations;

import com.generation.opticvbeckend.model.entities.User;

public class RequestData
{

	private static final ThreadLocal<User> requestData = new ThreadLocal<User>();

	public static void setUser(User u)
	{
		requestData.set(u);
	}

	public static User getUser()
	{
		return requestData.get();
	}

	public static void clear()
	{
		requestData.remove();
	}
}
