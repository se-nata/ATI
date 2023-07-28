package ru.se_nata.ati.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.se_nata.ati.entity.FormFrequency;
import ru.se_nata.ati.kafka.KafkaProducer;
import ru.se_nata.ati.service.AtiServiceImpl;

@Controller
@RequestMapping("/formfrequency/")
public class AtiFormFrequencyController {
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private AtiServiceImpl atiServiceImpl;

	
	public AtiFormFrequencyController(KafkaProducer kafkaProducer, AtiServiceImpl atiServiceImpl) {
		this.kafkaProducer = kafkaProducer;
		this.atiServiceImpl = atiServiceImpl;
	}
	@GetMapping("/")
	public String listFormFrequency(Model model) {
	    List<FormFrequency> formfrequency=atiServiceImpl.findAllFormFrequency();
		model.addAttribute("formfrequency",formfrequency);
		return "formfrequency/list";
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") int id,Model uiModel) {
		uiModel.addAttribute("formfrequency", atiServiceImpl.findbyIdFormFrequency(id));
		return "formfrequency/edit";
}   
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "update/{id}")
	public String update(@ModelAttribute("formfrequency") FormFrequency formfrequency, @PathVariable("id") int id, Model uiModel) {
		atiServiceImpl.saveFormFrequency(formfrequency);
		return "redirect:/formfrequency/";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "insert/")
	public String newForm( Model model) {
		FormFrequency formfrequency=new FormFrequency();
		model.addAttribute("formfrequency",formfrequency);
		return "formfrequency/insert";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("formfrequency") FormFrequency formfrequency) {
	atiServiceImpl.saveFormFrequency(formfrequency);
	kafkaProducer.sendMessage(formfrequency.toString());
		return "redirect:/formfrequency/";
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
		if (atiServiceImpl.findbyIdFormFrequency(id)!=null)
			atiServiceImpl.deleteFormFrequency(id);
		return "redirect:/formfrequency/";
	}
}
