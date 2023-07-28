package ru.se_nata.ati.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="act_has_form")

public class ActHasForm implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    @ManyToOne
    @JoinColumn(name="act_id")
	private RegulatoryAct actId;
    
    @ManyToOne
    @JoinColumn(name="form_id")
	private RegulatoryForm formId;     
    @Column(name = "note")
	private String note;
    
    public ActHasForm(Integer id, RegulatoryAct actId, RegulatoryForm formId, String note) {
		super();
		this.id = id;
		this.actId = actId;
		this.formId = formId;
		this.note = note;
	}
	public ActHasForm() {
	
	}
	
	public RegulatoryAct getActId() {
		return actId;
	}

	public void setActId(RegulatoryAct actId) {
		this.actId = actId;
	}

	

	public RegulatoryForm getFormId() {
		return formId;
	}

	public void setFormId(RegulatoryForm formId) {
		this.formId = formId;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "ActHasForm [id=" + id + ", actId=" + actId + ", formId=" + formId + ", note=" + note + "]";
	}



    
}
