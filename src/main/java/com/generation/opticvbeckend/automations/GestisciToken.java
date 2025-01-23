package com.generation.opticvbeckend.automations;

import com.generation.opticvbeckend.controllers.CredentialService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.generation.opticvbeckend.model.entities.User;

import java.io.IOException;

@Component
public class GestisciToken implements Filter
{

	@Autowired
	CredentialService cs;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
	{
		try
		{
			HttpServletRequest request = (HttpServletRequest) servletRequest;

			String token = request.getParameter("token");

			if(token==null || token.isEmpty())
				filterChain.doFilter(servletRequest, servletResponse);
			else
			{
			User u = cs.getUserByToken(token);

			RequestData.setUser(u);
			filterChain.doFilter(servletRequest, servletResponse);
			}
		}
		finally
		{
			RequestData.clear();
		}


	}

}
