package ru.se_nata.ati.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "regulatory_act")
public class RegulatoryAct implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description",nullable = true)
	private String description;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy ="actId")
	private Set<ActHasForm> acthasform = new HashSet<ActHasForm>();
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="leftActId")
	private Set<ActRelation> actrelationleft = new HashSet<ActRelation>();
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="rightActId")
	private Set<ActRelation> actrelationright = new HashSet<ActRelation>();
	
	public RegulatoryAct() {
		
	}


	public RegulatoryAct(Integer id, String number, Date date, String name, String description) {
		super();
		this.id = id;
		this.number = number;
		this.date = date;
		this.name = name;
		this.description = description;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	
	  
	  public Set<ActHasForm> getActhasform() { return acthasform; }
	  
	  
	  public void setActhasform(Set<ActHasForm> acthasform) { this.acthasform =
	  acthasform; }
	  
	  
	  public Set<ActRelation> getActrelationleft() { return actrelationleft; }
	  
	  
	  public void setActrelationleft(Set<ActRelation> actrelationleft) {
	  this.actrelationleft = actrelationleft; }
	  
	  
	  public Set<ActRelation> getActrelationright() { return actrelationright; }
	  
	  
	  public void setActrelationright(Set<ActRelation> actrelationright) {
	  this.actrelationright = actrelationright; }


	@Override
	public String toString() {
		return "RegulatoryAct [id=" + id + ", number=" + number + ", date=" + date + ", name=" + name + ", description="
				+ description + ", acthasform=" + acthasform + ", actrelationleft=" + actrelationleft
				+ ", actrelationright=" + actrelationright + "]";
	}
	 
	
	
	
}
