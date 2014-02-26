package com.vaadin.cdi.example;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vaadin.cdi.CDIUIProvider;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;

@WebServlet(value = "/*", asyncSupported = true)
public class Servlet extends VaadinServlet {

	public static final String AUTH_ORIGINALPAGE = "auth.originalpage";

	private UIProvider uiProvider = new CDIUIProvider();

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();
		getService().addSessionInitListener(new SessionInitListener() {

			@Override
			public void sessionInit(SessionInitEvent event)
					throws ServiceException {
				event.getSession().addUIProvider(uiProvider);

			}
		});
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * This haxi is only needed for app servers that for some reason lose
		 * request type on FORDARD.
		 * 
		 * Works (without): JBoss 7.1, Glassfish 4 Broken: Wildfly, TomEE
		 */
		if (request.getDispatcherType() == DispatcherType.FORWARD
				&& request.getRequestURI().contains("login")) {
			Object page = request
					.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
			request.getSession(true).setAttribute(AUTH_ORIGINALPAGE, page);
			response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", request.getRequestURI());
		} else {
			super.service(request, response);
		}
	}

}
