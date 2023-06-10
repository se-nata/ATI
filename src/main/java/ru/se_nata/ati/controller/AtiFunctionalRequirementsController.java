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

import ru.se_nata.ati.entity.ActHasForm;
import ru.se_nata.ati.entity.FunctionalRequirements;
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.service.AtiServiceImpl;

@Controller
@RequestMapping("/functionalrequirements/")
public class AtiFunctionalRequirementsController {
	
	@Autowired
	private AtiServiceImpl atiServiceImpl;

	public AtiFunctionalRequirementsController(AtiServiceImpl atiServiceImpl) {
		this.atiServiceImpl = atiServiceImpl;
	}
	
	@GetMapping("/")
	public String listRegulatoryAct(Model model) {
		List<FunctionalRequirements> functionalrequirements=atiServiceImpl.findAllFunctionalRequirements();
		model.addAttribute("functionalrequirements",functionalrequirements);
	    return "functionalrequirements/list";
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") int id,Model uiModel) {
		uiModel.addAttribute("functionalrequirements", atiServiceImpl.findbyIdFunctionalRequirements(id));
		return "functionalrequirements/edit";
}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "update/{id}")
	public String update(@ModelAttribute("functionalrequirements") FunctionalRequirements functionalrequirements,@PathVariable("id") int id, Model uiModel) {
		atiServiceImpl.saveFunctionalRequirements(functionalrequirements);
		return "redirect:/functionalrequirements/";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "insert/")
	public String insert( Model model) {
		FunctionalRequirements functionalrequirements=new FunctionalRequirements();
		model.addAttribute("functionalrequirements",functionalrequirements);
		return "functionalrequirements/insert";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("functionalrequirements") FunctionalRequirements functionalrequirements) {
	atiServiceImpl.saveFunctionalRequirements(functionalrequirements);
		return "redirect:/functionalrequirements/";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model uiModel) {
		if (atiServiceImpl.findbyIdFunctionalRequirements(id)!=null)
			atiServiceImpl.deleteFunctionalRequirements(id);
		return "redirect:/functionalrequirements/";
	}
}
