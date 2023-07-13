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
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.entity.RegulatoryForm;
import ru.se_nata.ati.service.AtiServiceImpl;
@Controller
@RequestMapping("/acthasform/")
public class AtiActHasFormController {
	@Autowired
	private AtiServiceImpl atiServiceImpl;

	public void setAtiServiceImpl(AtiServiceImpl atiServiceImpl) {
		this.atiServiceImpl = atiServiceImpl;
	}
	@GetMapping()
	public String listActHasForm(Model model) {
	    List<ActHasForm> acthasform=atiServiceImpl.findAllActHasForm();
		model.addAttribute("acthasform",acthasform);
		return "acthasform/list";
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") int id,Model model) {
		model.addAttribute("acthasform", atiServiceImpl.findbyIdActHasForm(id));
		return "acthasform/edit";
		
}   @PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "update/{id}")
	public String update(@ModelAttribute("acthasform") ActHasForm acthasform, @PathVariable("id") int id, Model uiModel) {
		atiServiceImpl.saveActHasForm(acthasform);
		return "redirect:/acthasform/";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "insert/")
	public String newForm( Model model) {
		ActHasForm acthasform=new ActHasForm();
		List<RegulatoryAct> actlist=atiServiceImpl.findAllRegulatoryAct();
		List<RegulatoryForm> formlist=atiServiceImpl.findAllRegulatoryForm();
		model.addAttribute("actlist",actlist);
		model.addAttribute("formlist",formlist);
		model.addAttribute("acthasform",acthasform);
		return "acthasform/insert";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("acthasform") ActHasForm acthasform) {
	atiServiceImpl.saveActHasForm(acthasform);
		return "redirect:/acthasform/";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
		if (atiServiceImpl.findbyIdActHasForm(id)!=null)
			atiServiceImpl.deleteActHasForm(id);
		return "redirect:/acthasform/";
	}
}

