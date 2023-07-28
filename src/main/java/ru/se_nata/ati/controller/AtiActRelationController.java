
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
import ru.se_nata.ati.entity.ActRelation;
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.entity.RelationType;
import ru.se_nata.ati.kafka.KafkaProducer;
import ru.se_nata.ati.service.AtiServiceImpl;

@Controller
@RequestMapping("/actrelation/")
public class AtiActRelationController {
	@Autowired
	private KafkaProducer kafkaProducer;
	@Autowired
	private AtiServiceImpl atiServiceImpl;


	public AtiActRelationController(KafkaProducer kafkaProducer, AtiServiceImpl atiServiceImpl) {
		this.kafkaProducer = kafkaProducer;
		this.atiServiceImpl = atiServiceImpl;
	}

	@GetMapping("/")
	public String listActRelation(Model model) {
		model.addAttribute("actrelation", atiServiceImpl.findAllActRelation());
		return "actrelation/list";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") int id, Model uiModel) {
		uiModel.addAttribute("actrelation", atiServiceImpl.findbyIdActRelation(id));
		return "actrelation/edit";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("update/{id}")
	public String update(@ModelAttribute("actrelation") ActRelation actrelation, @PathVariable("id") int id,
			Model uiModel) {
		atiServiceImpl.saveActRelation(actrelation);
		return "redirect:/actrelation/";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("insert/")
	public String newForm(Model model) {
		ActRelation actrelation = new ActRelation();
		List<RegulatoryAct> left = atiServiceImpl.findAllRegulatoryAct();
		List<RegulatoryAct> right = atiServiceImpl.findAllRegulatoryAct();
		List<RelationType> relationtype = atiServiceImpl.findAllRelationType();
		model.addAttribute("left", left);
		model.addAttribute("right", right);
		model.addAttribute("relationtype", relationtype);
		model.addAttribute("actrelation", actrelation);
		return "actrelation/insert";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("actrelation") ActRelation actrelation) {
		atiServiceImpl.saveActRelation(actrelation);
		kafkaProducer.sendMessage(actrelation.toString());
		return "redirect:/actrelation/";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
		if (atiServiceImpl.findbyIdActRelation(id) != null)
			atiServiceImpl.deleteActRelation(id);
		return "redirect:/actrelation/";
	}

}
