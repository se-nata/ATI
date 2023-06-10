package ru.se_nata.ati.service;

import java.util.List;

import ru.se_nata.ati.entity.ActHasForm;
import ru.se_nata.ati.entity.ActRelation;
import ru.se_nata.ati.entity.FormFrequency;
import ru.se_nata.ati.entity.FormHasFrequency;
import ru.se_nata.ati.entity.FunctionalRequirements;
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.entity.RegulatoryForm;
import ru.se_nata.ati.entity.RelationType;

public interface AtiServise {
	
	List <RegulatoryAct> findAllRegulatoryAct();
	List <RegulatoryForm> findAllRegulatoryForm();
	List <RelationType>findAllRelationType(); 
	List <ActHasForm> findAllActHasForm();
	List <ActRelation> findAllActRelation();
	List <FormFrequency> findAllFormFrequency();
	List <FunctionalRequirements> findAllFunctionalRequirements();
	List <FormHasFrequency> findAllFormHasFrequency();
	
	RegulatoryAct findbyIdRegulatoryAct(Integer id);
	RegulatoryForm findbyIdRegulatoryForm(Integer id);
	RelationType findbyIdRelationType(Integer id);
	ActHasForm findbyIdActHasForm(Integer id);
	ActRelation findbyIdActRelation(Integer id);
	FormFrequency findbyIdFormFrequency(Integer id);
	FunctionalRequirements findbyIdFunctionalRequirements(Integer id);
	FormHasFrequency findbyIdFormHasFrequency(Integer id);
	
	void saveRegulatoryAct(RegulatoryAct regulatoryAct); 
	void saveRegulatoryForm (RegulatoryForm  regulatoryForm);
	void saveRelationType (RelationType  relationType);
	void saveActHasForm (ActHasForm  actHasForm);
	void saveActRelation (ActRelation actRelation);
	void saveFormFrequency (FormFrequency formFrequency);
	void saveFunctionalRequirements (FunctionalRequirements functionalRequirements);
	void saveFormHasFrequency (FormHasFrequency formHasFrequency);
	
	void deleteRegulatoryAct(Integer id);
	void deleteRegulatoryForm(Integer id);
	void deleteRelationType(Integer id);
	void deleteActHasForm(Integer id);
	void deleteActRelation(Integer id);
	void deleteFormFrequency(Integer id);
	void deleteFunctionalRequirements(Integer id);
	void deleteFormHasFrequency(Integer id);
}

