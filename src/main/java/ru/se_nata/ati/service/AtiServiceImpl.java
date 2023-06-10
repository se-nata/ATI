package ru.se_nata.ati.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import jakarta.transaction.Transactional;
import ru.se_nata.ati.entity.ActHasForm;
import ru.se_nata.ati.entity.ActRelation;
import ru.se_nata.ati.entity.FormFrequency;
import ru.se_nata.ati.entity.FormHasFrequency;
import ru.se_nata.ati.entity.FunctionalRequirements;
import ru.se_nata.ati.entity.RegulatoryAct;
import ru.se_nata.ati.entity.RegulatoryForm;
import ru.se_nata.ati.entity.RelationType;
import ru.se_nata.ati.repository.AtiActHasFormRepository;
import ru.se_nata.ati.repository.AtiActRelationRepository;
import ru.se_nata.ati.repository.AtiFormFrequencyRepository;
import ru.se_nata.ati.repository.AtiFormHasFrequencyRepository;
import ru.se_nata.ati.repository.AtiFunctionalRequirementsRepository;
import ru.se_nata.ati.repository.AtiRegulatoryActRepository;
import ru.se_nata.ati.repository.AtiRegulatoryFormRepository;
import ru.se_nata.ati.repository.AtiRelationTypeRepository;

@Repository
@Service
@Transactional
public class AtiServiceImpl implements AtiServise{
	
	private AtiActHasFormRepository atiActHasFormRepository;
	private AtiActRelationRepository atiActRelationRepository;
	private AtiFormFrequencyRepository atiFormFrequencyRepository;
	private AtiRegulatoryActRepository atiRegulatoryActRepository;
	private AtiRegulatoryFormRepository atiRegulatoryFormRepository;
	private AtiRelationTypeRepository  atiRelationTypeRepository;
	private AtiFunctionalRequirementsRepository atiFunctionalRequirementsRepository;
	private AtiFormHasFrequencyRepository atiFormHasFrequencyRepository;
	
	@Autowired
	public void setAtiRegulatoryActRepository(AtiRegulatoryActRepository atiRegulatoryActRepository) {
		this.atiRegulatoryActRepository = atiRegulatoryActRepository;
	}
	

	@Autowired
	public void setAtiActHasFormRepository(AtiActHasFormRepository atiActHasFormRepository) {
		this.atiActHasFormRepository = atiActHasFormRepository;
	}
	@Autowired
	public void setAtiActRelationRepository(AtiActRelationRepository
	  atiActRelationRepository) { this.atiActRelationRepository =
	  atiActRelationRepository; }
	 
	@Autowired
	public void setAtiFormFrequencyRepository(AtiFormFrequencyRepository atiFormFrequencyRepository) {
		this.atiFormFrequencyRepository = atiFormFrequencyRepository;
	}
	
	@Autowired
	public void setAtiRegulatoryFormRepository(AtiRegulatoryFormRepository atiRegulatoryFormRepository) {
		this.atiRegulatoryFormRepository = atiRegulatoryFormRepository;
	}

	
	@Autowired 
	public void setAtiRelationTypeRepository(AtiRelationTypeRepository
	  atiRelationTypeRepository) { this.atiRelationTypeRepository =
	  atiRelationTypeRepository; }
	  
	
	@Autowired 
	public void setAtiFunctionalRequirementsRepository(AtiFunctionalRequirementsRepository atiFunctionalRequirementsRepository) {
		this.atiFunctionalRequirementsRepository = atiFunctionalRequirementsRepository;
	}
	@Autowired
	public void setAtiFormHasFrequencyRepository(AtiFormHasFrequencyRepository atiFormHasFrequencyRepository) {
			this.atiFormHasFrequencyRepository = atiFormHasFrequencyRepository;
		}
	
	
	@Override
	public List<RegulatoryAct> findAllRegulatoryAct() {
	return Lists.newArrayList(atiRegulatoryActRepository.findAll()); 
	}
	
	  @Override 
	  public List<RegulatoryForm> findAllRegulatoryForm() { return
	  Lists.newArrayList(atiRegulatoryFormRepository.findAll()); }
	  
	  
	  @Override
	  public List<RelationType> findAllRelationType() { return
	  Lists.newArrayList(atiRelationTypeRepository.findAll()); }
	  
	  @Override
	  public List<ActHasForm> findAllActHasForm() { return
	  Lists.newArrayList(atiActHasFormRepository.findAll()); }
	  
	  
	  @Override 
	  public List<ActRelation> findAllActRelation() { return
	  Lists.newArrayList(atiActRelationRepository.findAll()); }
	  
	  @Override
	  public List<FormFrequency> findAllFormFrequency() { return
	  Lists.newArrayList(atiFormFrequencyRepository.findAll()); }
	  
	 
	  @Override
	  public List<FunctionalRequirements> findAllFunctionalRequirements() {
			return Lists.newArrayList(atiFunctionalRequirementsRepository.findAll()); 
		}
	 
	  @Override
	  public List<FormHasFrequency> findAllFormHasFrequency() {
			return  Lists.newArrayList(atiFormHasFrequencyRepository.findAll()); 
		}
	
	@Override
	public RegulatoryAct findbyIdRegulatoryAct(Integer id) {
		return atiRegulatoryActRepository.findById(id).get();
	}
   
	@Override
	public RegulatoryForm findbyIdRegulatoryForm(Integer id) {
		return  atiRegulatoryFormRepository.findById(id).get();
	}

	
	  @Override
	  public RelationType findbyIdRelationType(Integer id) { return
	  atiRelationTypeRepository.findById(id).get(); }
	 

	@Override
	public ActHasForm findbyIdActHasForm(Integer id) {
		return  atiActHasFormRepository.findById(id).get();
	}

	
	@Override
	public ActRelation findbyIdActRelation(Integer id) { return
	  atiActRelationRepository.findById(id).get(); }
	 

	@Override
	public FormFrequency findbyIdFormFrequency(Integer id) {
		return  atiFormFrequencyRepository.findById(id).get();
	}
	
	
	@Override
	public FunctionalRequirements findbyIdFunctionalRequirements(Integer id) {
		return atiFunctionalRequirementsRepository.findById(id).get();
	}
	
	@Override
	public FormHasFrequency findbyIdFormHasFrequency(Integer id) {
		
		return atiFormHasFrequencyRepository.findById(id).get();
	}

	@Override
	public void saveRegulatoryAct(RegulatoryAct regulatoryAct) {
		atiRegulatoryActRepository.save(regulatoryAct);
		
	}

	@Override
	public void saveRegulatoryForm(RegulatoryForm regulatoryForm) {
		atiRegulatoryFormRepository.save(regulatoryForm);
		
	}

	
	 @Override 
	 public void saveRelationType(RelationType relationType) {
	  atiRelationTypeRepository.save(relationType);
	  
	  }
	 
	@Override
	public void saveActHasForm(ActHasForm actHasForm) {
		atiActHasFormRepository.save(actHasForm);
		
	}

	
	@Override
	public void saveActRelation(ActRelation actRelation) {
	  atiActRelationRepository.save(actRelation);
	  
	  }
	 
	@Override
	public void saveFormFrequency(FormFrequency formFrequency) {
		atiFormFrequencyRepository.save(formFrequency);
	}
	
	
	@Override
	public void saveFunctionalRequirements(FunctionalRequirements functionalRequirements) {
		atiFunctionalRequirementsRepository.save(functionalRequirements);
		
	}
	@Override
	public void saveFormHasFrequency(FormHasFrequency formHasFrequency) {
		atiFormHasFrequencyRepository.save(formHasFrequency);
		
	}


	@Override
	public void deleteRegulatoryAct(Integer id) {
		atiRegulatoryActRepository.deleteById(id);
		
	}

	@Override
	public void deleteRegulatoryForm(Integer id) {
		atiRegulatoryFormRepository.deleteById(id);
		
	}

	
	@Override
	public void deleteRelationType(Integer id) {
	  atiRelationTypeRepository.deleteById(id);
	  
	  }
	 

	@Override
	public void deleteActHasForm(Integer id) {
		atiActHasFormRepository.deleteById(id);
		
	}

	
	@Override 
	public void deleteActRelation(Integer id) {
	  atiActRelationRepository.deleteById(id);
	  
	  }
	 

	@Override
	public void deleteFormFrequency(Integer id) {
		atiFormFrequencyRepository.deleteById(id);
		
	}

	

	@Override
	public void deleteFunctionalRequirements(Integer id) {
		atiFunctionalRequirementsRepository.deleteById(id);
		
	}
	
	@Override
	public void deleteFormHasFrequency(Integer id) {
		atiFormHasFrequencyRepository.deleteById(id);
		
	}
	

}
