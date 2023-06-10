package ru.se_nata.ati.formatter;

import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.service.AtiServiceImpl;

@Component
public class RegulatoryActFormatter implements Formatter<RegulatoryAct> {
	

	 AtiServiceImpl atiServiceImpl ;


	public RegulatoryActFormatter(AtiServiceImpl atiServiceImpl) {
		super();
		this.atiServiceImpl = atiServiceImpl;
	}

	public AtiServiceImpl getAtiServiceImpl() {
		return atiServiceImpl;
	}

	public void setAtiServiceImpl(AtiServiceImpl atiServiceImpl) {
		this.atiServiceImpl = atiServiceImpl;
	}

	@Override
	public String print(RegulatoryAct regulatoryAct, Locale locale) {

		return String.valueOf(regulatoryAct.getId());
	}

	@Override
	public RegulatoryAct parse(String text, Locale locale) throws ParseException {
		
		return atiServiceImpl.findbyIdRegulatoryAct(Integer.parseInt(text));
	}

}
