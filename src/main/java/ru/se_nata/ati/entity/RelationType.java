  package ru.se_nata.ati.entity;
  
  import java.util.HashSet; 
  import java.util.Set;
  import jakarta.persistence.Column;
  import jakarta.persistence.Entity;
  import jakarta.persistence.FetchType;
  import jakarta.persistence.GeneratedValue;
  import jakarta.persistence.GenerationType;
  import jakarta.persistence.Id;
  import jakarta.persistence.OneToMany; 
  import jakarta.persistence.Table;
  
  @Entity
  @Table(name = "relation_type") 
  public class RelationType {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name = "description") 
  private String description;

  @OneToMany(mappedBy="relationTypeId", fetch=FetchType.LAZY)
  private Set<ActRelation> relationTypeId = new HashSet<ActRelation>();
  
  public Integer getId() { 
	  return id; }
  public void setId(Integer id) {
	  this.id = id;
  } 
  public String getDescription() { 
	  return description; } 
  public void setDescription(String description) {
	  this.description = description; }
  

  
  
  }
 