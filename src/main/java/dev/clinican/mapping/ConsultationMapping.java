package dev.clinican.mapping;

import dev.clinican.dto.ConsultationDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.Patient;
import dev.clinican.entity.TbUser;
import dev.clinican.repository.DoctorRepository;
import dev.clinican.repository.PatientRepository;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Component;

@Component
public class ConsultationMapping {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TbUserRepository tbUserRepository;

    public ConsultationMapping(DoctorRepository doctorRepository,
                               PatientRepository patientRepository,
                               TbUserRepository tbUserRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.tbUserRepository = tbUserRepository;
    }


    public Consultation toEntity(ConsultationDto consultationDto) {
        Doctor doctor = doctorRepository.findById(consultationDto.doctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found" + consultationDto.doctorId()));
        Patient patient = patientRepository.findById(consultationDto.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found" + consultationDto.patientId()));
        TbUser tbUser = tbUserRepository.findById(consultationDto.createdBy())
                .orElseThrow(() -> new RuntimeException("TbUser not found" + consultationDto.createdBy()));

        Consultation consultation = new Consultation();
        consultation.setDoctor(doctor);
        consultation.setConsultationDate(consultationDto.consultationDate());
        consultation.setPatient(patient);
        consultation.setConsultationStatus(consultationDto.consultationStatus());
        consultation.setReason(consultationDto.reason());
        consultation.setNote(consultationDto.note());
        consultation.setCreatedBy(tbUser);


        return consultation;
    }
    public ConsultationDto toDto(Consultation consultation) {
        return new ConsultationDto(
                consultation.getId(),
                consultation.getDoctor().getId(),
                consultation.getPatient().getId(),
                consultation.getConsultationDate(),
                consultation.getConsultationStatus(),
                consultation.getReason(),
                consultation.getNote(),
                consultation.getCreatedBy().getId()
        );
    }
}
