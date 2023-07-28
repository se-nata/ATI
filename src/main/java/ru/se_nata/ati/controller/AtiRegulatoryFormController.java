package ru.se_nata.ati.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.se_nata.ati.entity.ActHasForm;
import ru.se_nata.ati.entity.FormFrequency;
import ru.se_nata.ati.entity.FormHasFrequency;
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.entity.RegulatoryForm;
import ru.se_nata.ati.kafka.KafkaProducer;
import ru.se_nata.ati.service.AtiServiceImpl;

@Controller
@RequestMapping("/regulatoryform/")
public class AtiRegulatoryFormController {
	@Autowired
	private KafkaProducer kafkaProducer; 
	
	@Autowired
	private AtiServiceImpl atiServiceImpl;


	public AtiRegulatoryFormController(KafkaProducer kafkaProducer, AtiServiceImpl atiServiceImpl) {
		this.kafkaProducer = kafkaProducer;
		this.atiServiceImpl = atiServiceImpl;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setLenient(false);

	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	@GetMapping("/")
	public String listRegulatoryForm(Model model) {
	    List<RegulatoryForm> regulatoryform=atiServiceImpl.findAllRegulatoryForm();
		model.addAttribute("regulatoryform",regulatoryform);
		return "regulatoryform/list";
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") int id,Model uiModel) {
		uiModel.addAttribute("regulatoryform", atiServiceImpl.findbyIdRegulatoryForm(id));
		return "regulatoryform/edit";
}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "update/{id}")
	public String update(@ModelAttribute("regulatoryform") RegulatoryForm regulatoryform, @PathVariable("id") int id, Model uiModel) {
		atiServiceImpl.saveRegulatoryForm(regulatoryform);
		return "redirect:/regulatoryform/";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "insert/")
	public String newForm( Model model) {
		RegulatoryForm regulatoryform=new RegulatoryForm();
		model.addAttribute("regulatoryform",regulatoryform);
		return "regulatoryform/insert";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("regulatoryform") RegulatoryForm regulatoryform) {
	atiServiceImpl.saveRegulatoryForm(regulatoryform);
	kafkaProducer.sendMessage(regulatoryform.toString());
		return "redirect:/regulatoryform/";
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "create/")
	public String create( Model model) {
		RegulatoryForm regulatoryform=new RegulatoryForm();
		
		ActHasForm acthasform=new ActHasForm();
		List<RegulatoryAct> actlist=atiServiceImpl.findAllRegulatoryAct();
	    List<FormFrequency> formfrequencylist=atiServiceImpl.findAllFormFrequency();
		model.addAttribute("actlist",actlist);
		model.addAttribute("regulatoryform",regulatoryform);
		model.addAttribute("acthasform",acthasform);
		model.addAttribute("formfrequencylist",formfrequencylist);
		return "regulatoryform/create";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "create/")
	public String create(@ModelAttribute("regulatoryform") RegulatoryForm regulatoryform,@ModelAttribute("acthasform")ActHasForm acthasform,@RequestParam(value = "cers" , required = false) int[] cers ) {
		System.out.println("начинаем вывод"+  regulatoryform);
		regulatoryform.getFormFrequency().forEach(o->System.out.println(o));
	atiServiceImpl.saveRegulatoryForm(regulatoryform);
	 acthasform.setFormId(regulatoryform);
	 atiServiceImpl.saveActHasForm(acthasform);
		
		  if(cers != null) 
		  { FormFrequency formFrequency = null ; 
		  for (int i = 0; i < cers.length; i++) {
		  formFrequency = new FormFrequency();
		  formFrequency.setId(cers[i]);
		  regulatoryform.getFormFrequency().add(formFrequency);
		  }
		  for (int a = 0; a < regulatoryform.getFormFrequency().size(); a++) {
				FormHasFrequency formHasFrequency=new FormHasFrequency();
	            formHasFrequency.setFormId(regulatoryform);
	            formHasFrequency.setFrequencyId(regulatoryform.getFormFrequency().get(a));
	            atiServiceImpl.saveFormHasFrequency(formHasFrequency);
	        }
		  } 
	
		return "redirect:/regulatoryform/";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
		if (atiServiceImpl.findbyIdRegulatoryForm(id)!=null)
			atiServiceImpl.deleteRegulatoryForm(id);
		return "redirect:/regulatoryform/";
	}
}
