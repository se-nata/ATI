package ru.se_nata.ati.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "regulatory_form")
public class RegulatoryForm {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(name = "okud_cd")
		private String okudCd;
		
		@Column(name = "form_nm")
		private String formNm;
		
		@Column(name = "form_note",nullable = false)
		private String formNote;
		
		@Column(name = "eff_dt")
		private Date effDt;
		
		@Column(name = "end_dt")
		private  Date endDt;
		
		@Column(name = "aff_dt",nullable = true)
		private Date affDt;
		
		@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL ,mappedBy="formId")
		private Set<ActHasForm> acthasform=new HashSet<ActHasForm>();
		
		@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
		@JoinTable(name="form_has_frequency",joinColumns = @JoinColumn(name="form_id"),inverseJoinColumns = @JoinColumn(name="frequency_id"))
		private List<FormFrequency> formFrequency=new ArrayList<FormFrequency>();
		
		
		
		public RegulatoryForm(Integer id, String okudCd, String formNm, String formNote, Date effDt, Date endDt,
				Date affDt) {
			super();
			this.id = id;
			this.okudCd = okudCd;
			this.formNm = formNm;
			this.formNote = formNote;
			this.effDt = effDt;
			this.endDt = endDt;
			this.affDt = affDt;
		
		}

		public RegulatoryForm() {
			
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getOkudCd() {
			return okudCd;
		}

		public void setOkudCd(String okudCd) {
			this.okudCd = okudCd;
		}

		public String getFormNm() {
			return formNm;
		}

		public void setFormNm(String formNm) {
			this.formNm = formNm;
		}

		public String getFormNote() {
			return formNote;
		}

		public void setFormNote(String formNote) {
			this.formNote = formNote;
		}

		public Date getEffDt() {
			return effDt;
		}

		public void setEffDt(Date effDt) {
			this.effDt = effDt;
		}

		public Date getEndDt() {
			return endDt;
		}

		public void setEndDt(Date endDt) {
			this.endDt = endDt;
		}

		

		public Date getAffDt() {
			return affDt;
		}

		public void setAffDt(Date affDt) {
			this.affDt = affDt;
		}

	

		public Set<ActHasForm> getActhasform() {
			return acthasform;
		}

		public void setActhasform(Set<ActHasForm> acthasform) {
			this.acthasform = acthasform;
		}

		public List<FormFrequency> getFormFrequency() {
			return formFrequency;
		}

		public void setFormFrequency(List<FormFrequency> formFrequency) {
			this.formFrequency = formFrequency;
		}

		@Override
		public int hashCode() {
			return Objects.hash(formFrequency);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RegulatoryForm other = (RegulatoryForm) obj;
			if(id==null) {
				if(other.id !=null)
					return false;
			}
			else if (!id.equals(other.id))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "RegulatoryForm [id=" + id + ", okudCd=" + okudCd + ", formNm=" + formNm + ", formNote=" + formNote
					+ ", effDt=" + effDt + ", endDt=" + endDt + ", affDt=" + affDt + ", acthasform=" + acthasform
					+ ", formFrequency=" + formFrequency + "]";
		}
		
}
