package ru.se_nata.ati.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.se_nata.ati.controller.AtiActHasFormController;
import ru.se_nata.ati.entity.*;
import ru.se_nata.ati.repository.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtiServiceImplTest {
    @Mock
    AtiActHasFormRepository atiActHasFormRepository;
    @Mock
    AtiActRelationRepository atiActRelationRepository;
    @Mock
    AtiFormFrequencyRepository atiFormFrequencyRepository;
    @Mock
    AtiRegulatoryActRepository atiRegulatoryActRepository;
    @Mock
    AtiRegulatoryFormRepository atiRegulatoryFormRepository;
    @Mock
    AtiRelationTypeRepository  atiRelationTypeRepository;
    @Mock
    AtiFunctionalRequirementsRepository atiFunctionalRequirementsRepository;
    @Mock
    AtiFormHasFrequencyRepository atiFormHasFrequencyRepository;
    @InjectMocks
    AtiServiceImpl atiServiceImpl;
    @MockBean
    UserRepository UserRepository;
    List<ActHasForm> getActHasForm() {

        RegulatoryAct newoneRegulatoryAct = new RegulatoryAct(1, "4927-У", new Date(2018 - 01 - 10),
                "О перечне, формах и порядке составления и представления форм",
                "указание регулирует составление отчетности");
        RegulatoryForm newoneRegulatoryForm = new RegulatoryForm(1, "0403202",
                "Сведения об оценке выполнения операторами", "форма ДИБ (нерегулярная)", new Date(2019 - 03 - 30),
                new Date(2022 - 12 - 12), new Date(2012 - 01 - 13));

        RegulatoryAct newtwoRegulatoryAct = new RegulatoryAct(1, "4847-У", new Date(2018 - 01 - 11),
                "О ВНЕСЕНИИ ИЗМЕНЕНИЙ В УКАЗАНИЕ ", "регулирует составления отчетности");
        RegulatoryForm newtwoRegulatoryForm = new RegulatoryForm(1, "0403582", "Сведения о событиях,",
                "Форма ДИБ (Квартальна", new Date(2019 - 03 - 23), new Date(2023 - 01 - 12), new Date(2022 - 12 - 12));

        ActHasForm oneActHasForm = new ActHasForm(1, newoneRegulatoryAct, newoneRegulatoryForm, "Новая редакция формы");
        ActHasForm twoActHasForm = new ActHasForm(1, newtwoRegulatoryAct, newtwoRegulatoryForm, "Новая редакция формы");
        return List.of(oneActHasForm, twoActHasForm);

    }
    List<RegulatoryAct> getRegulatoryAct() {

        RegulatoryAct newoneRegulatoryAct = new RegulatoryAct(1, "4927-У", new Date(2018 - 01 - 10),
                "О перечне, формах и порядке составления и представления форм",
                "указание регулирует составление отчетности");
        RegulatoryAct newotwoRegulatoryAct = new RegulatoryAct(1, "0403202",new Date(2018 - 01 - 10),
                "Сведения об оценке выполнения операторами", "форма ДИБ (нерегулярная)");
        return List.of(newoneRegulatoryAct , newotwoRegulatoryAct);

    }
    List<RegulatoryForm> getRegulatoryForm() {

        RegulatoryForm newoneRegulatoryForm  = new RegulatoryForm (1, "4927-У",
                "О перечне, формах и порядке составления и представления форм",
                "указание регулирует составление отчетности",new Date(2018 - 01 - 10),new Date(2018 - 01 - 10),new Date(2018 - 01 - 10));
        RegulatoryForm  newotwoRegulatoryForm  = new RegulatoryForm (1, "0403202",
                "Сведения об оценке выполнения операторами", "форма ДИБ (нерегулярная)",new Date(2018 - 01 - 10),new Date(2018 - 01 - 10),new Date(2018 - 01 - 10));
        return List.of(newoneRegulatoryForm , newotwoRegulatoryForm);

    }
    List<RelationType> getRelationType() {

        RelationType newoneRelationType  = new RelationType (1, "Test"
              );
        RelationType  newotwoRelationType  = new RelationType(1, "Test"
                );
        return List.of(newoneRelationType , newotwoRelationType );

    }
    List<ActRelation> getActRelation() {

        RegulatoryAct newoneRegulatoryActleft = new RegulatoryAct(1, "4927-У", new Date(2018 - 01 - 10),
                "О перечне, формах и порядке составления и представления форм",
                "указание регулирует составление отчетности");
        RegulatoryAct newotwoRegulatoryActleft = new RegulatoryAct(1, "0403202",new Date(2018 - 01 - 10),
                "Сведения об оценке выполнения операторами", "форма ДИБ (нерегулярная)");
        RegulatoryAct newoneRegulatoryActright = new RegulatoryAct (1, "4927-У", new Date(2018 - 01 - 10),
                "О перечне, формах и порядке составления и представления форм",
                "указание регулирует составление отчетности");
        RegulatoryAct  newotwoRegulatoryActright  = new RegulatoryAct (2, "5038-У", new Date(2018 - 01 - 10),
                "О  формах и порядке составления и представления форм",
                "указание регулирует составление отчетности");
        RelationType newoneRelationType  = new RelationType (1, "Test"
        );
        RelationType  newotwoRelationType  = new RelationType(1, "Test"
        );
        ActRelation newoneActRelation  = new ActRelation (1, newoneRegulatoryActleft,newoneRegulatoryActright,newoneRelationType);
        ActRelation  newotwoActRelation  = new ActRelation (1, newotwoRegulatoryActleft,newoneRegulatoryActright,newotwoRelationType);
        return List.of(newoneActRelation  , newoneActRelation );

    }
    List<FormFrequency> getFormFrequency() {

        FormFrequency newoneFormFrequency = new FormFrequency (1, "годовая","q");
        FormFrequency  newotwoFormFrequency  = new FormFrequency(1, "квартальная","s");
        return List.of(newoneFormFrequency , newoneFormFrequency );

    }
    List<FunctionalRequirements> getFunctionalRequirements() {

        FunctionalRequirements newoneFunctionalRequirements = new FunctionalRequirements(1, "ФТ-45-2-2/223", new Date(2023 - 05 - 25),
                "Функциональные требования на формирование аналитических таблиw",
                "РО 0403204");
        FunctionalRequirements newtwoFunctionalRequirements  = new FunctionalRequirements(1, "ФТ-56-2-1/234",new Date(2023 - 05 - 25),
                "СФункциональные требования к сбору и обработке информации ", "	ожидмемая дата ввода в эксплуатацию 01.01.2024");
        return List.of(newoneFunctionalRequirements , newtwoFunctionalRequirements );

    }
    List<FormHasFrequency> getFormHasFrequency() {

        RegulatoryForm regulatoryForm  = new RegulatoryForm (1, "4927-У",
                "О перечне, формах и порядке составления и представления форм",
                "указание регулирует составление отчетности",new Date(2018 - 01 - 10),new Date(2018 - 01 - 10),new Date(2018 - 01 - 10));
        FormFrequency formFrequency = new FormFrequency (1, "годовая","q");
        RegulatoryForm regulatoryForm2  = new RegulatoryForm (1, "4927-У",
                "О перечне, формах и порядке составления и представления форм",
                "указание регулирует составление отчетности",new Date(2018 - 01 - 10),new Date(2018 - 01 - 10),new Date(2018 - 01 - 10));
        FormFrequency formFrequency2 = new FormFrequency (1, "годовая","q");
        FormHasFrequency newoneFormHasFrequency = new FormHasFrequency(1, regulatoryForm, formFrequency );
        FormHasFrequency newtwoFormHasFrequency  = new FormHasFrequency(1, regulatoryForm2,formFrequency2);
        return List.of(newoneFormHasFrequency , newoneFormHasFrequency);

    }
    @Test
    void findAllRegulatoryAct() {
        when(atiRegulatoryActRepository.findAll()).thenReturn(getRegulatoryAct());
        assertThat(atiServiceImpl.findAllRegulatoryAct()).hasSize(2);
        verify(atiRegulatoryActRepository, times(1)).findAll();
        verifyNoMoreInteractions(atiRegulatoryActRepository);
    }

    @Test
    void findAllRegulatoryForm() {
        when(atiRegulatoryFormRepository.findAll()).thenReturn(getRegulatoryForm());
        assertThat(atiServiceImpl.findAllRegulatoryForm()).hasSize(2);
        verify(atiRegulatoryFormRepository, times(1)).findAll();
        verifyNoMoreInteractions(atiRegulatoryFormRepository);
    }

    @Test
    void findAllRelationType() {
        when(atiRelationTypeRepository.findAll()).thenReturn(getRelationType());
        assertThat(atiServiceImpl.findAllRelationType()).hasSize(2);
        verify(atiRelationTypeRepository,times(1)).findAll();
        verifyNoMoreInteractions(atiRelationTypeRepository);
    }

    @Test
    void findAllActHasForm() {
        when(atiActHasFormRepository.findAll()).thenReturn(getActHasForm());
        assertThat(atiServiceImpl.findAllActHasForm()).hasSize(2);
        verify(atiActHasFormRepository, times(1)).findAll();
        verifyNoMoreInteractions(atiActHasFormRepository);
    }

    @Test
    void findAllActRelation() {
        when(atiActRelationRepository.findAll()).thenReturn(getActRelation());
        assertThat(atiServiceImpl.findAllActRelation()).hasSize(2);
        verify(atiActRelationRepository,timeout(1)).findAll();
        verifyNoMoreInteractions(atiActRelationRepository);
    }

    @Test
    void findAllFormFrequency() {
        when(atiFormFrequencyRepository.findAll()).thenReturn(getFormFrequency());
        assertThat(atiServiceImpl.findAllFormFrequency()).hasSize(2);
        verify(atiFormFrequencyRepository,timeout(1)).findAll();
        verifyNoMoreInteractions(atiFormFrequencyRepository);
    }

    @Test
    void findAllFunctionalRequirements() {
        when(atiFunctionalRequirementsRepository.findAll()).thenReturn(getFunctionalRequirements());
        assertThat(atiServiceImpl.findAllFunctionalRequirements()).hasSize(2);
        verify(atiFunctionalRequirementsRepository,times(1)).findAll();
        verifyNoMoreInteractions(atiFunctionalRequirementsRepository);
    }

    @Test
    void findAllFormHasFrequency() {
        when(atiFormHasFrequencyRepository.findAll()).thenReturn(getFormHasFrequency());
        assertThat(atiServiceImpl.findAllFormHasFrequency()).hasSize(2);
        verify(atiFormHasFrequencyRepository,times(1)).findAll();
        verifyNoMoreInteractions(atiFormHasFrequencyRepository);
    }

    @Test
    void findbyIdRegulatoryAct() {
        List <RegulatoryAct>list=getRegulatoryAct();
        RegulatoryAct regulatoryAct=list.get(1);
        when(atiRegulatoryActRepository.findById(anyInt())).thenReturn(Optional.of(regulatoryAct));
        var oneReglatoryAct=atiServiceImpl.findbyIdRegulatoryAct(getRandomInt());
        assertThat(oneReglatoryAct).usingRecursiveComparison().isEqualTo(regulatoryAct);
        verify(atiRegulatoryActRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiRegulatoryActRepository);
    }

    @Test
    void findbyIdRegulatoryForm() {
        List <RegulatoryForm>list=getRegulatoryForm();
        RegulatoryForm regulatoryForm=list.get(1);
        when(atiRegulatoryFormRepository.findById(anyInt())).thenReturn(Optional.of(regulatoryForm));
        var oneReglatoryForm=atiServiceImpl.findbyIdRegulatoryForm(getRandomInt());
        assertThat(oneReglatoryForm).usingRecursiveComparison().isEqualTo(regulatoryForm);
        verify(atiRegulatoryFormRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiRegulatoryFormRepository);
    }

    @Test
    void findbyIdRelationType() {
        List <RelationType>list=getRelationType();
        RelationType relationType=list.get(1);
        when(atiRelationTypeRepository.findById(anyInt())).thenReturn(Optional.of(relationType));
        var oneReltionType=atiServiceImpl.findbyIdRelationType(getRandomInt());
        assertThat(oneReltionType).usingRecursiveComparison().isEqualTo(relationType);
        verify(atiRelationTypeRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiRelationTypeRepository);
    }

    @Test
    void findbyIdActHasForm() {
        List <ActHasForm>list=getActHasForm();
        var actHasForm=list.get(1);
        when(atiActHasFormRepository.findById(anyInt())).thenReturn(Optional.of(actHasForm));
        var oneActHasForm=atiServiceImpl.findbyIdActHasForm(getRandomInt());
        assertThat(oneActHasForm).usingRecursiveComparison().isEqualTo(actHasForm);
        verify(atiActHasFormRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiActHasFormRepository);
    }

    @Test
    void findbyIdActRelation() {
        List <ActRelation>list=getActRelation();
        var actRelation=list.get(1);
        when(atiActRelationRepository.findById(anyInt())).thenReturn(Optional.of(actRelation));
        var oneActRalation=atiServiceImpl.findbyIdActRelation(getRandomInt());
        assertThat(oneActRalation).usingRecursiveComparison().isEqualTo(actRelation);
        verify(atiActRelationRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiActRelationRepository);
    }

    @Test
    void findbyIdFormFrequency() {
        List <FormFrequency>list=getFormFrequency();
        var formFrequency=list.get(1);
        when(atiFormFrequencyRepository.findById(anyInt())).thenReturn(Optional.of(formFrequency));
        var oneFormFreency=atiServiceImpl.findbyIdFormFrequency(getRandomInt());
        assertThat(oneFormFreency).usingRecursiveComparison().isEqualTo(formFrequency);
        verify(atiFormFrequencyRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiFormFrequencyRepository);
    }

    @Test
    void findbyIdFunctionalRequirements() {
        List <FunctionalRequirements>list=getFunctionalRequirements();
        var functionalRequirements=list.get(1);
        when(atiFunctionalRequirementsRepository.findById(anyInt())).thenReturn(Optional.of(functionalRequirements));
        var oneFunctionalRequirements=atiServiceImpl.findbyIdFunctionalRequirements(getRandomInt());
        assertThat(oneFunctionalRequirements).usingRecursiveComparison().isEqualTo(functionalRequirements);
        verify(atiFunctionalRequirementsRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiFunctionalRequirementsRepository);
    }

    @Test
    void findbyIdFormHasFrequency() {
        List <FormHasFrequency>list=getFormHasFrequency();
        var formHasFrequency=list.get(1);
        when(atiFormHasFrequencyRepository.findById(anyInt())).thenReturn(Optional.of(formHasFrequency));
        var oneFormHasFrequency=atiServiceImpl.findbyIdFormHasFrequency(getRandomInt());
        assertThat(oneFormHasFrequency).usingRecursiveComparison().isEqualTo(formHasFrequency);
        verify(atiFormHasFrequencyRepository,times(1)).findById(anyInt());
        verifyNoMoreInteractions(atiFormHasFrequencyRepository);
    }

    @Test
    void saveRegulatoryAct() {
        List <RegulatoryAct>list=getRegulatoryAct();
        RegulatoryAct regulatoryAct=list.get(1);
      atiServiceImpl.saveRegulatoryAct(regulatoryAct);
      verify(atiRegulatoryActRepository,times(1)).save(regulatoryAct);

    }

    @Test
    void saveRegulatoryForm() {
        List <RegulatoryForm>list=getRegulatoryForm();
        RegulatoryForm regulatoryForm=list.get(1);
        atiServiceImpl.saveRegulatoryForm(regulatoryForm);
        verify(atiRegulatoryFormRepository,times(1)).save(regulatoryForm);
    }

    @Test
    void saveRelationType() {
        List <RelationType>list=getRelationType();
        RelationType relationType=list.get(1);
        atiServiceImpl.saveRelationType(relationType);
        verify(atiRelationTypeRepository,times(1)).save(relationType);
    }

    @Test
    void saveActHasForm() {
        List <ActHasForm>list=getActHasForm();
        var actHasForm=list.get(1);
        atiServiceImpl.saveActHasForm(actHasForm);
        verify(atiActHasFormRepository,times(1)).save(actHasForm);

    }

    @Test
    void saveActRelation() {
        List <ActRelation>list=getActRelation();
        var actRelation=list.get(1);
        atiServiceImpl.saveActRelation(actRelation);
        verify(atiActRelationRepository,times(1)).save(actRelation);
    }

    @Test
    void saveFormFrequency() {
        List <FormFrequency>list=getFormFrequency();
        var formFrequency=list.get(1);
        atiServiceImpl.saveFormFrequency(formFrequency);
        verify(atiFormFrequencyRepository,times(1)).save(formFrequency);
    }

    @Test
    void saveFunctionalRequirements() {
        List <FunctionalRequirements>list=getFunctionalRequirements();
        var functionalRequirements=list.get(1);
        atiServiceImpl.saveFunctionalRequirements(functionalRequirements);
        verify(atiFunctionalRequirementsRepository,times(1)).save(functionalRequirements);
    }

    @Test
    void saveFormHasFrequency() {
        List <FormHasFrequency>list=getFormHasFrequency();
        var formHasFrequency=list.get(1);
        atiServiceImpl.saveFormHasFrequency(formHasFrequency);
        verify(atiFormHasFrequencyRepository,times(1)).save(formHasFrequency);
    }

    @Test
    void deleteRegulatoryAct() {
        doNothing().when(atiRegulatoryActRepository).deleteById(anyInt());
        atiServiceImpl.deleteRegulatoryAct(getRandomInt());
        verify(atiRegulatoryActRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiRegulatoryActRepository);
    }

    @Test
    void deleteRegulatoryForm() {
        doNothing().when(atiRegulatoryFormRepository).deleteById(anyInt());
        atiServiceImpl.deleteRegulatoryForm(getRandomInt());
        verify(atiRegulatoryFormRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiRegulatoryFormRepository);
    }

    @Test
    void deleteRelationType() {
        doNothing().when(atiRelationTypeRepository).deleteById(anyInt());
        atiServiceImpl.deleteRelationType(getRandomInt());
        verify(atiRelationTypeRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiRelationTypeRepository);
    }

    @Test
    void deleteActHasForm() {
        doNothing().when(atiActHasFormRepository).deleteById(anyInt());
        atiServiceImpl.deleteActHasForm(getRandomInt());
        verify(atiActHasFormRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiActHasFormRepository);
    }

    @Test
    void deleteActRelation() {
        doNothing().when(atiActRelationRepository).deleteById(anyInt());
        atiServiceImpl.deleteActRelation(getRandomInt());
        verify(atiActRelationRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiActRelationRepository);
    }

    @Test
    void deleteFormFrequency() {
        doNothing().when(atiFormFrequencyRepository).deleteById(anyInt());
        atiServiceImpl.deleteFormFrequency(getRandomInt());
        verify(atiFormFrequencyRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiFormHasFrequencyRepository);
    }

    @Test
    void deleteFunctionalRequirements() {
        doNothing().when(atiFunctionalRequirementsRepository).deleteById(anyInt());
        atiServiceImpl.deleteFunctionalRequirements(getRandomInt());
        verify(atiFunctionalRequirementsRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiFunctionalRequirementsRepository);
    }

    @Test
    void deleteFormHasFrequency() {
        doNothing().when(atiFormHasFrequencyRepository).deleteById(anyInt());
        atiServiceImpl.deleteFormHasFrequency(getRandomInt());
        verify(atiFormHasFrequencyRepository,times(1)).deleteById(anyInt());
        verifyNoMoreInteractions(atiFormHasFrequencyRepository);
    }


    private int getRandomInt() {
        return new Random().ints(1, 10).findFirst().getAsInt();
}}
