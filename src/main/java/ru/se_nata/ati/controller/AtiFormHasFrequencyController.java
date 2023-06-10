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

import ru.se_nata.ati.entity.FormHasFrequency;
import ru.se_nata.ati.service.AtiServiceImpl;

@Controller
@RequestMapping("/formhasfrequency/")
public class AtiFormHasFrequencyController {
	@Autowired
	private AtiServiceImpl atiServiceImpl;

	public void setAtiServiceImpl(AtiServiceImpl atiServiceImpl) {
		this.atiServiceImpl = atiServiceImpl;
	}
	
	@GetMapping("/")
	public String listFormHasFrequency(Model model) {
		model.addAttribute("formhasfrequency",atiServiceImpl.findAllFormHasFrequency());
		return "formhasfrequency/list";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") Integer id, Model model) {
	model.addAttribute("listformfrequency", atiServiceImpl.findAllFormFrequency());
	model.addAttribute("listregulatoryform", atiServiceImpl.findAllRegulatoryForm());
	model.addAttribute("formhasfrequency", atiServiceImpl.findbyIdFormHasFrequency(id));
	return "formhasfrequency/edit";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "update/{id}")
	public String update(@ModelAttribute("formhasfrequency") FormHasFrequency formHasFrequency,@PathVariable("id") Integer id, Model model) {
		atiServiceImpl.saveFormHasFrequency(formHasFrequency);
		return "redirect:/formhasfrequency/";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "insert/")
	public String insertform(Model model) {
		FormHasFrequency formHasFrequency=new FormHasFrequency();
		model.addAttribute("listformfrequency", atiServiceImpl.findAllFormFrequency());
		model.addAttribute("listregulatoryform", atiServiceImpl.findAllRegulatoryForm());
		model.addAttribute("formhasfrequency",formHasFrequency);
		return "formhasfrequency/insert";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("formHasFrequency")FormHasFrequency formHasFrequency) {
		atiServiceImpl.saveFormHasFrequency(formHasFrequency);
		return "redirect:/formhasfrequency/";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		if (atiServiceImpl.findbyIdFormHasFrequency(id)!=null)
        atiServiceImpl.deleteFormHasFrequency(id);
        return "redirect:/formhasfrequency/";
	}
}