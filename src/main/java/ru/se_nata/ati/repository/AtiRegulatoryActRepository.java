package ru.se_nata.ati.repository;




import org.springframework.data.repository.CrudRepository;

import ru.se_nata.ati.entity.RegulatoryAct;

public interface AtiRegulatoryActRepository extends CrudRepository<RegulatoryAct, Integer>{
   
}
