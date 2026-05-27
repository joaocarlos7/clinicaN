package dev.clinican.service;


import dev.clinican.dto.ConsultationDto;
import dev.clinican.dto.DoctorDto;
import dev.clinican.entity.Consultation;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.Patient;
import dev.clinican.entity.TbUser;
import dev.clinican.repository.DoctorRepository;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final TbUserRepository tbUserRepository;

    public DoctorService(DoctorRepository doctorRepository, TbUserRepository tbUserRepository) {
        this.doctorRepository = doctorRepository;
        this.tbUserRepository = tbUserRepository;
    }

    // Convert Methods

        // DTO to Entity (Entry)
    private Doctor toEntity(DoctorDto doctorDto) {
        TbUser user = tbUserRepository.findById(doctorDto.userId())
                .orElseThrow(()-> new RuntimeException("User not found"));


        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setCrm(doctorDto.crm());
        doctor.setSpeciality(doctorDto.speciality());
        doctor.setPhoneNumber(doctorDto.phoneNumber());

        return doctor;
        }

        // Entity To DTO (Exit)
    private DoctorDto toDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getUser().getId(),
                doctor.getCrm(),
                doctor.getSpeciality(),
                doctor.getPhoneNumber()
        );
    }


    // Public Methods

    // Create
    public DoctorDto create(DoctorDto doctorDto) {

        Doctor doctor = toEntity(doctorDto);
        Doctor saveDoctor = doctorRepository.save(doctor);

        return toDto(saveDoctor);
    }

    // Update
    public DoctorDto update(UUID id, DoctorDto doctorDto) {
        TbUser user = tbUserRepository.findById(doctorDto.userId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found" + doctorDto.id()));
        doctor.setUser(user);
        doctor.setCrm(doctorDto.crm());
        doctor.setSpeciality(doctorDto.speciality());
        doctor.setPhoneNumber(doctorDto.phoneNumber());



        return toDto(doctorRepository.save(doctor));




    }

    // Delete
    public void delete(UUID id) {
        doctorRepository.deleteById(id);
    }

    // List By Doctor Id
    public DoctorDto findById(UUID id) {
        return doctorRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Doctor not found" + id));
    }
    // List By Doctor Name
    public List<DoctorDto> findByDoctorName(String name) {
        return doctorRepository
                .findByDoctorNameContainingIgnoreCase(name)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }

    // List By Doctor CRM
    public DoctorDto findByCrm(String crm) {
        return doctorRepository.findByCrmContainingIgnoreCase(crm)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("CRM not found" + crm));
    }

}
