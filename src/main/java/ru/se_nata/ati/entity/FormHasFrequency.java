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
@Table(name = "form_has_frequency")
public class FormHasFrequency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "form_id")
    private RegulatoryForm formId;
    @ManyToOne
    @JoinColumn(name = "frequency_id")
    private FormFrequency frequencyId;

    public FormHasFrequency(Integer id, RegulatoryForm formId, FormFrequency frequencyId) {
        this.id = id;
        this.formId = formId;
        this.frequencyId = frequencyId;
    }

    public FormHasFrequency() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RegulatoryForm getFormId() {
        return formId;
    }

    public void setFormId(RegulatoryForm formId) {
        this.formId = formId;
    }

    public FormFrequency getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(FormFrequency frequencyId) {
        this.frequencyId = frequencyId;
    }

    @Override
    public String toString() {
        return "FormHasFrequency [id=" + id + ", formId=" + formId + ", frequencyId=" + frequencyId + "]";
    }


}
