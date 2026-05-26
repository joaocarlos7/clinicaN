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

    private Doctor toEntity(DoctorDto doctorDto) {
        TbUser user = tbUserRepository.findById(doctorDto.userId())
                .orElseThrow(()-> new RuntimeException("User not found"));


        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setName(doctorDto.name());
        doctor.setCrm(doctorDto.crm());
        doctor.setSpeciality(doctorDto.speciality());
        doctor.setPhoneNumber(doctorDto.phoneNumber());

        return doctor;
        }
    private DoctorDto toDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getUser().getId(),
                doctor.getCrm(),
                doctor.getName(),
                doctor.getSpeciality(),
                doctor.getPhoneNumber()
        );
    }

    public DoctorDto create(DoctorDto doctorDto) {

        Doctor doctor = toEntity(doctorDto);
        Doctor saveDoctor = doctorRepository.save(doctor);

        return toDto(saveDoctor);
    }
    public DoctorDto update(UUID id, DoctorDto doctorDto) {
        TbUser user = tbUserRepository.findById(doctorDto.userId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consultation not found" + doctorDto.id()));
        doctor.setUser(user);
        doctor.setName(doctorDto.name());
        doctor.setCrm(doctorDto.crm());
        doctor.setSpeciality(doctorDto.speciality());
        doctor.setPhoneNumber(doctorDto.phoneNumber());



        return toDto(doctorRepository.save(doctor));




    }
    public void delete(UUID id) {
        doctorRepository.deleteById(id);
    }
    public List<DoctorDto> findByDoctorName(String name) {
        return doctorRepository
                .findByDoctorNameContainingIgnoreCase(name)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }
    public List<DoctorDto> findByCrm(String crm) {
        return doctorRepository
                .findByCrmContainingIgnoreCase(crm)
                .stream()
                .map(this::toDto)
                .toList();
    }




}
