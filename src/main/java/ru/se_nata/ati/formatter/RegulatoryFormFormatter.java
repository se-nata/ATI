package ru.se_nata.ati.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ru.se_nata.ati.entity.RegulatoryForm;
import ru.se_nata.ati.service.AtiServiceImpl;
@Component
public class RegulatoryFormFormatter implements Formatter<RegulatoryForm> {

     AtiServiceImpl atiServiceImpl;
	
	
	public AtiServiceImpl getAtiServiceImpl() {
		return atiServiceImpl;
	}

	public RegulatoryFormFormatter(AtiServiceImpl atiServiceImpl) {
		super();
		this.atiServiceImpl = atiServiceImpl;
	}

	public void setAtiServiceImpl(AtiServiceImpl atiServiceImpl) {
		this.atiServiceImpl = atiServiceImpl;
	}
	
	
	@Override
	public String print(RegulatoryForm regulatoryForm, Locale locale) {
		return String.valueOf(regulatoryForm.getId());
	}
	
	@Override
	public RegulatoryForm parse(String text, Locale locale) throws ParseException {
		return atiServiceImpl.findbyIdRegulatoryForm(Integer.parseInt(text));
	}



}
