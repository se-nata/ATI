package ru.se_nata.ati;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.se_nata.ati.formatter.FormFrequencyFormatter;
import ru.se_nata.ati.formatter.RegulatoryActFormatter;
import ru.se_nata.ati.formatter.RegulatoryFormFormatter;
import ru.se_nata.ati.service.AtiServiceImpl;

@EnableAutoConfiguration
@EntityScan(basePackages = "ru.se_nata.ati.entity")
@SpringBootApplication
@ComponentScan(basePackages = { "ru.se_nata.ati.formatter", "ru.se_nata.ati.service", "ru.se_nata.ati.controller","ru.se_nata.ati.repository","ru.se_nata.ati.security","ru.se_nata.ati.kafka"})
@EnableTransactionManagement
public class AtiApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {

		SpringApplication.run(AtiApplication.class, args);
	}
	
	 @EnableWebMvc
	 @Configuration 
	 static class MyConfig implements WebMvcConfigurer {
	 
	 @Autowired 
	 AtiServiceImpl atiservise;
	 @Override
	 public void addFormatters(FormatterRegistry registry) {
	  registry.addFormatter(new RegulatoryActFormatter(atiservise));
	 registry.addFormatter(new RegulatoryFormFormatter(atiservise));
	 registry.addFormatter(new FormFrequencyFormatter(atiservise)); }
	  
	 @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
		
     }
	
	 }
	 
}
