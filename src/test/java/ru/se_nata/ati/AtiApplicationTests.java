package ru.se_nata.ati;


import org.springframework.beans.factory.annotation.Autowired;
import
  org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import ru.se_nata.ati.controller.AtiActHasFormController;
  
  @SpringBootTest 
  class AtiApplicationTests {
	  @Autowired
		private AtiActHasFormController controller;

		@Test
		void contextLoads() {
		
		assertThat(controller).isNotNull();
}  }
 