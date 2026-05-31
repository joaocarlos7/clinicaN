package dev.clinican.service;


import dev.clinican.dto.DoctorDto;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.TbUser;
import dev.clinican.exception.DoctorAlreadyExistisException;
import dev.clinican.mapping.DoctorMapping;
import dev.clinican.repository.DoctorRepository;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final TbUserRepository tbUserRepository;
    private final DoctorMapping doctorMapping;

    public DoctorService(DoctorRepository doctorRepository,
                         TbUserRepository tbUserRepository,
                         DoctorMapping doctorMapping) {
        this.doctorRepository = doctorRepository;
        this.tbUserRepository = tbUserRepository;
        this.doctorMapping = doctorMapping;
    }

    // Public Methods

    // Create
    public DoctorDto create(DoctorDto doctorDto) {
        if(doctorRepository.existsByCrm(doctorDto.crm())) {
            throw new DoctorAlreadyExistisException(doctorDto.crm());
        } try {
            Doctor doctor = doctorMapping.toEntity(doctorDto);
            Doctor saveDoctor = doctorRepository.save(doctor);
            return doctorMapping.toDto(saveDoctor);
        } catch (DoctorAlreadyExistisException e) {
            throw new DoctorAlreadyExistisException(doctorDto.crm());}
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

        if (doctorRepository.existsByCrm(doctorDto.crm())) {
            throw new DoctorAlreadyExistisException(doctorDto.crm());
        } try {
            return doctorMapping.toDto(doctorRepository.save(doctor));
        } catch (DoctorAlreadyExistisException e) {
            throw new DoctorAlreadyExistisException(doctorDto.crm());}}

    // Delete
    public void delete(UUID id) {
    doctorRepository.deleteById(id);
}

    // List By Doctor Id
    public DoctorDto findById(UUID id) {
    return doctorRepository.findById(id)
            .map(doctorMapping::toDto)
            .orElseThrow(() -> new RuntimeException("Doctor not found" + id));
}

    // List By Doctor Name
    public List<DoctorDto> findByDoctorName(String name) {
    return doctorRepository
            .findByUserNameContainingIgnoreCase(name)
            .stream() // Take the list one by one
            .map(doctorMapping::toDto)// Convert in Dto
            .toList(); // List
}

    // List By Doctor CRM
    public DoctorDto findByCrm(Integer crm) {
    return doctorRepository.findByCrmContainingIgnoreCase(crm)
            .map(doctorMapping::toDto)
            .orElseThrow(() -> new RuntimeException("CRM not found" + crm));
}

}
