package dev.clinican.mapping;

import dev.clinican.dto.PrescriptionDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.Patient;
import dev.clinican.entity.Prescription;
import dev.clinican.repository.ConsultationRepository;
import dev.clinican.repository.DoctorRepository;
import dev.clinican.repository.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapping {

    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public PrescriptionMapping(ConsultationRepository consultationRepository,
                               DoctorRepository doctorRepository,
                               PatientRepository patientRepository) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }



    public PrescriptionDto toDto(Prescription prescription) {
        return new PrescriptionDto(
                prescription.getId(),
                prescription.getConsultation().getId(),
                prescription.getDoctor().getId(),
                prescription.getPatient().getId(),
                prescription.getCreatedAt(),
                prescription.getObservation());
    }


    public Prescription toEntity(PrescriptionDto prescriptionDto) {
        Consultation consultation = consultationRepository.findById(prescriptionDto.consultationID())
                .orElseThrow(() -> new RuntimeException("Consultation not found" + prescriptionDto.consultationID()));
        Patient patient = patientRepository.findById(prescriptionDto.patientID())
                .orElseThrow(() -> new RuntimeException("Patient not found" + prescriptionDto.patientID()));
        Doctor doctor = doctorRepository.findById(prescriptionDto.doctorID())
                .orElseThrow(() -> new RuntimeException("Doctor not found" + prescriptionDto.doctorID()));

        Prescription prescription = new Prescription();
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        prescription.setConsultation(consultation);
        prescription.setObservation(prescriptionDto.observation());
        prescription.setCreatedAt(prescriptionDto.createdAt());

        return prescription;
    }
}
