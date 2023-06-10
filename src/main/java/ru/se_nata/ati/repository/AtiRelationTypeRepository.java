
  package ru.se_nata.ati.repository;
  
  import org.springframework.data.repository.CrudRepository;
  
  import ru.se_nata.ati.entity.RelationType;
  
  public interface AtiRelationTypeRepository extends
  CrudRepository<RelationType, Integer>{
  
  }
 