package ru.se_nata.ati.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="form_frequency")
public class FormFrequency implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="frequency_nm")
	private String frequencyNm;
	
	@Column(name = "frequency_cd")
	private String frequencyCd;
    @ManyToMany(mappedBy = "formFrequency")
	private Set<RegulatoryForm> formFrequency=new HashSet<RegulatoryForm>();
	
	public FormFrequency() {
	
	}

	public FormFrequency(Integer id, String frequencyNm, String frequencyCd) {
		this.id = id;
		this.frequencyNm = frequencyNm;
		this.frequencyCd = frequencyCd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFrequencyNm() {
		return frequencyNm;
	}

	public void setFrequencyNm(String frequencyNm) {
		this.frequencyNm = frequencyNm;
	}

	public String getFrequencyCd() {
		return frequencyCd;
	}

	public void setFrequencyCd(String frequencyCd) {
		this.frequencyCd = frequencyCd;
	}

	@Override
	public String toString() {
		return "FormFrequency [id=" + id + ", frequencyNm=" + frequencyNm + ", frequencyCd=" + frequencyCd
				+ ", formFrequency=" + formFrequency + "]";
	}






	
	
}
