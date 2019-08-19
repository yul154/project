package com.sample;

import org.springframework.context.annotation.ComponentScan;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sample")

public class WebConfig implements WebMvcConfigurer {
    
	@Bean
	public MessageSource messageSource() {//
		ResourceBundleMessageSource messagesource= new ResourceBundleMessageSource();
		messagesource.setBasename("messages");//prefix of the file 
		return  messagesource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {// store local information in session.
		SessionLocaleResolver resolver= new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {// detect change information
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("language");
		registry.addInterceptor(changeInterceptor);
	}
	
	
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	} 
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pdfs/**").addResourceLocations("/WEB-INF/pdf/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
		
	}
	
}