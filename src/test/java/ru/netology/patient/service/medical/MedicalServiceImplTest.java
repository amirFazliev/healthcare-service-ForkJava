package ru.netology.patient.service.medical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicalServiceImplTest {

    @Test
    public void checkTemperatureTestNever() {
        String patientId = "id1";
        BigDecimal temperature = new BigDecimal("36.5");

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(patientId))
                .thenReturn(new PatientInfo(patientId, "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);
        Mockito.doCallRealMethod().when(sendAlertService).send(Mockito.anyString());

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(patientId, temperature);

        Mockito.verify(sendAlertService, Mockito.never()).send(Mockito.any());
    }

    @Test
    public void checkTemperatureTestOne() {
        String patientId = "id1";
        BigDecimal temperature = new BigDecimal("34.5");

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(patientId))
                .thenReturn(new PatientInfo(patientId, "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);
        Mockito.doCallRealMethod().when(sendAlertService).send(Mockito.anyString());

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(patientId, temperature);

        Mockito.verify(sendAlertService, Mockito.times(1)).send(Mockito.any());
    }

    @Test
    public void checkTemperatureTestOneCaptor() {
        String patientId = "id1";
        BigDecimal temperature = new BigDecimal("34.5");

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(patientId))
                .thenReturn(new PatientInfo(patientId, "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkTemperature(patientId, temperature);

        Mockito.verify(sendAlertService).send(argumentCaptor.capture());
        Assertions.assertEquals(String.format("Warning, patient with id: %s, need help", patientId), argumentCaptor.getValue());
    }

    @Test
    public void checkBloodPressureTestNever() {
        String patientId = "id1";
        BloodPressure bloodPressure = new BloodPressure(120, 80);

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(patientId))
                .thenReturn(new PatientInfo(patientId, "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);
        Mockito.doCallRealMethod().when(sendAlertService).send(Mockito.anyString());

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkBloodPressure(patientId, bloodPressure);

        Mockito.verify(sendAlertService, Mockito.never()).send(Mockito.any());
    }

    @Test
    public void checkBloodPressureTestOne() {
        String patientId = "id1";
        BloodPressure bloodPressure = new BloodPressure(80, 120);

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(patientId))
                .thenReturn(new PatientInfo(patientId, "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);
        Mockito.doCallRealMethod().when(sendAlertService).send(Mockito.anyString());

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkBloodPressure(patientId, bloodPressure);

        Mockito.verify(sendAlertService, Mockito.times(1)).send(Mockito.any());
    }

    @Test
    public void checkBloodPressureTestOneCaptor() {
        String patientId = "id1";
        BloodPressure bloodPressure = new BloodPressure(80, 120);

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById(patientId))
                .thenReturn(new PatientInfo(patientId, "Иван", "Петров", LocalDate.of(1980, 11, 26),
                        new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80))));

        SendAlertService sendAlertService = Mockito.mock(SendAlertServiceImpl.class);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, sendAlertService);
        medicalService.checkBloodPressure(patientId, bloodPressure);

        Mockito.verify(sendAlertService).send(argumentCaptor.capture());
        Assertions.assertEquals(String.format("Warning, patient with id: %s, need help", patientId), argumentCaptor.getValue());
    }
}
