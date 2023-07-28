package ru.se_nata.ati.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.se_nata.ati.entity.RelationType;
import ru.se_nata.ati.kafka.KafkaProducer;
import ru.se_nata.ati.service.AtiServiceImpl;

@Controller
@RequestMapping("/relationtype/")
public class AtiRelationTypeController {
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private AtiServiceImpl atiServiceImpl;

	public AtiRelationTypeController(KafkaProducer kafkaProducer, AtiServiceImpl atiServiceImpl) {
		this.kafkaProducer = kafkaProducer;
		this.atiServiceImpl = atiServiceImpl;
	}
	@GetMapping()
	public String listRelationType(Model model) {
		model.addAttribute("relationtype",atiServiceImpl.findAllRelationType());
		return "relationtype/list";
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") int id,Model uiModel) {
		uiModel.addAttribute("relationtype", atiServiceImpl.findbyIdRelationType(id));
		return "relationtype/edit";
}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("update/{id}")
	public String update(@ModelAttribute("relationtype") RelationType relationtype, @PathVariable("id") int id, Model uiModel) {
		atiServiceImpl.saveRelationType(relationtype);
		return "redirect:/relationtype/";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( "insert/")
	public String newForm( Model model) {
		RelationType relationtype=new RelationType();
		model.addAttribute("relationtype",relationtype);
		return "relationtype/insert";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("relationtype")RelationType relationtype) {
	atiServiceImpl.saveRelationType(relationtype);
	kafkaProducer.sendMessage(relationtype.toString());
		return "redirect:/relationtype/";
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model uiModel) {
		if (atiServiceImpl.findbyIdRelationType(id)!=null)
			atiServiceImpl.deleteRelationType(id);
		return "redirect:/relationtype/";
	}
}
