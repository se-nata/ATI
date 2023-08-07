  package ru.se_nata.ati.entity;
  
  import java.io.Serializable;

import jakarta.persistence.Entity;
  import jakarta.persistence.GeneratedValue; 
  import jakarta.persistence.GenerationType; 
  import jakarta.persistence.Id; 
  import jakarta.persistence.JoinColumn;
  import jakarta.persistence.ManyToOne; 
  import jakarta.persistence.Table;
  
  @Entity
  @Table(name="act_relation") 
  public class ActRelation implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @ManyToOne()
  @JoinColumn(name="left_act_id") 
  private RegulatoryAct leftActId;
  
  @ManyToOne()
  @JoinColumn(name="right_act_id") 
  private RegulatoryAct rightActId;
  
  @ManyToOne()
  @JoinColumn(name="relation_type_id") 
  private RelationType relationTypeId;

    public ActRelation(Integer id, RegulatoryAct leftActId, RegulatoryAct rightActId, RelationType relationTypeId) {
      this.id = id;
      this.leftActId = leftActId;
      this.rightActId = rightActId;
      this.relationTypeId = relationTypeId;
    }

    public ActRelation() {
  
  }
  
  public Integer getId() { return id; }
  
  public void setId(Integer id) { this.id = id; }
  
  
  public RegulatoryAct getLeftActId() { return leftActId; }
  
  public void setLeftActId(RegulatoryAct leftActId) { this.leftActId =
  leftActId; }
  
  public RegulatoryAct getRightActId() { return rightActId; }
  
  public void setRightActId(RegulatoryAct rightActId) { this.rightActId =
  rightActId; }
  
  public RelationType getRelationTypeId() { return relationTypeId; }
  
  public void setRelationTypeId(RelationType relationTypeId) {
  this.relationTypeId = relationTypeId; }

@Override
public String toString() {
	return "ActRelation [id=" + id + ", leftActId=" + leftActId + ", rightActId=" + rightActId + ", relationTypeId="
			+ relationTypeId + "]";
}
 
  
  
  }
 