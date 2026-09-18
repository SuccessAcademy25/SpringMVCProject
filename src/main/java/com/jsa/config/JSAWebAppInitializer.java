package com.jsa.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class JSAWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println(" ** getRootConfigClasses **");
		return new Class[] { JSAWebConfig.class };
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println(" ** getServletConfigClasses **");
		return new Class[] { JSAWebConfig.class };
	}
	@Override
	protected String[] getServletMappings() {
		System.out.println(" ** getServletMappings **");
		//return new String[] {"/","*.jsa","*.do" };
		return new String[] {"/" };
	}
}
