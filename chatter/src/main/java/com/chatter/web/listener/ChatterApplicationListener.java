package com.chatter.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.chatter.web.util.ApplicationProperties;

public class ChatterApplicationListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		ApplicationProperties.loadProperties();
		
	}

}
