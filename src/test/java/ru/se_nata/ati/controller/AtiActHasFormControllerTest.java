package ru.se_nata.ati.controller;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.sql.Date;
import ru.se_nata.ati.entity.ActHasForm;
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.entity.RegulatoryForm;
import ru.se_nata.ati.kafka.KafkaProducer;
import ru.se_nata.ati.repository.UserRepository;
import ru.se_nata.ati.service.AtiServiceImpl;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AtiActHasFormController.class)
public class AtiActHasFormControllerTest {

     @Autowired
    MockMvc mvc;
    @InjectMocks
    AtiActHasFormController atiActHasFormController;
    @MockBean
    KafkaProducer kafkaProducer;
    @MockBean
    AtiServiceImpl atiServiceImpl;
    @MockBean
    UserRepository UserRepository;
  
    
  
	 List<ActHasForm> getActHasForm() {

		RegulatoryAct newoneRegulatoryAct = new RegulatoryAct(1, "4927-У", new Date(2018 - 01 - 10),
				"О перечне, формах и порядке составления и представления форм",
				"указание регулирует составление отчетности");
		RegulatoryForm newoneRegulatoryForm = new RegulatoryForm(1, "0403202",
				"Сведения об оценке выполнения операторами", "форма ДИБ (нерегулярная)", new Date(2019 - 03 - 30),
				new Date(2022 - 12 - 12), new Date(2012 - 01 - 13));

		RegulatoryAct newtwoRegulatoryAct = new RegulatoryAct(1, "4847-У", new Date(2018 - 01 - 11),
				"О ВНЕСЕНИИ ИЗМЕНЕНИЙ В УКАЗАНИЕ ", "регулирует составления отчетности");
		RegulatoryForm newtwoRegulatoryForm = new RegulatoryForm(1, "0403582", "Сведения о событиях,",
				"Форма ДИБ (Квартальна", new Date(2019 - 03 - 23), new Date(2023 - 01 - 12), new Date(2022 - 12 - 12));

		ActHasForm oneActHasForm = new ActHasForm(1, newoneRegulatoryAct, newoneRegulatoryForm, "Новая редакция формы");
		ActHasForm twoActHasForm = new ActHasForm(1, newtwoRegulatoryAct, newtwoRegulatoryForm, "Новая редакция формы");
		return List.of(oneActHasForm, twoActHasForm);

	}
	@Test
	void findAllActHasForm() throws Exception {
		Mockito.when(atiServiceImpl.findAllActHasForm()).thenReturn(getActHasForm());
		 mvc.perform(get("/acthasform/"))
				 .andExpect(status().isOk())
				 .andExpect(view().name("acthasform/list"))
				 .andExpect(model().attribute("acthasform", hasSize(2)));
	     verify(atiServiceImpl, times(1)).findAllActHasForm();
	     verifyNoMoreInteractions(atiServiceImpl);            
	}
	
}
