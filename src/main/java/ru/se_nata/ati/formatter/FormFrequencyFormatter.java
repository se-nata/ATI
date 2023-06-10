package ru.se_nata.ati.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import ru.se_nata.ati.entity.FormFrequency;
import ru.se_nata.ati.service.AtiServiceImpl;

  @Component public class FormFrequencyFormatter implements
  Formatter<FormFrequency>{
  
  
  AtiServiceImpl atiServiceImpl;
  
  
  public FormFrequencyFormatter(AtiServiceImpl atiServiceImpl) { super();
  this.atiServiceImpl = atiServiceImpl; } public AtiServiceImpl
  getAtiServiceImpl() { return atiServiceImpl; }
  
  public void setAtiServiceImpl(AtiServiceImpl atiServiceImpl) {
  this.atiServiceImpl = atiServiceImpl; }
  
  @Override public String print(FormFrequency formfrequency, Locale locale) {
  System.out.println("in the converter print: " + formfrequency); return
  String.valueOf(formfrequency.getId()); }
  
  @Override public FormFrequency parse(String text, Locale locale) throws
  ParseException {
  
  System.out.println("in the converter parse: " + text); return
  atiServiceImpl.findbyIdFormFrequency(Integer.parseInt(text)); }
  
  
  
  }
 
