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
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.kafka.KafkaProducer;
import ru.se_nata.ati.service.AtiServiceImpl;

@Controller
@RequestMapping("/regulatoryact/")
public class AtiRegulatoryActController {
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Autowired
	private AtiServiceImpl atiServiceImpl;

	public AtiRegulatoryActController(KafkaProducer kafkaProducer, AtiServiceImpl atiServiceImpl) {
	
		this.kafkaProducer = kafkaProducer;
		this.atiServiceImpl = atiServiceImpl;
	}
	@GetMapping("/")
	public String listRegulatoryAct(Model model) {
		List<RegulatoryAct> regulatoryact=atiServiceImpl.findAllRegulatoryAct();
		model.addAttribute("regulatoryact",regulatoryact);
	    return "regulatoryact/list";
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "update/{id}")
	public String updateform(@PathVariable("id") int id,Model uiModel) {
		uiModel.addAttribute("regulatoryact", atiServiceImpl.findbyIdRegulatoryAct(id));
		return "regulatoryact/edit";
}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "update/{id}")
	public String update(@ModelAttribute("regulatoryact") RegulatoryAct regulatoryact,@PathVariable("id") int id, Model uiModel) {
		atiServiceImpl.saveRegulatoryAct(regulatoryact);
		return "redirect:/regulatoryact/";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "insert/")
	public String insert( Model model) {
		RegulatoryAct regulatoryact=new RegulatoryAct();
		model.addAttribute("regulatoryact",regulatoryact);
		return "regulatoryact/insert";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "insert/")
	public String save(@ModelAttribute("regulatoryact") RegulatoryAct regulatoryact) {
	atiServiceImpl.saveRegulatoryAct(regulatoryact);
	kafkaProducer.sendMessage(regulatoryact.toString());
		return "redirect:/regulatoryact/";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") int id, Model uiModel) {
		if (atiServiceImpl.findbyIdRegulatoryAct(id)!=null)
			atiServiceImpl.deleteRegulatoryAct(id);
		return "redirect:/regulatoryact/";
	}

}
