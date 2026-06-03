package dev.clinican.service;


import dev.clinican.dto.DoctorDto;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.TbUser;
import dev.clinican.exception.CrmNotFound;
import dev.clinican.exception.DoctorAlreadyExistsException;
import dev.clinican.exception.DoctorNotFoundException;
import dev.clinican.exception.UserNotFoundException;
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

    public DoctorDto create(DoctorDto doctorDto) {
        if(doctorRepository.existsByCrm(doctorDto.crm())) {
            throw new DoctorAlreadyExistsException(doctorDto.crm());
        }
            Doctor doctor = doctorMapping.toEntity(doctorDto);
            Doctor saveDoctor = doctorRepository.save(doctor);
            return doctorMapping.toDto(saveDoctor);

    }

    public DoctorDto update(UUID id, DoctorDto doctorDto) {
        TbUser user = tbUserRepository.findById(doctorDto.userId())
                .orElseThrow(()-> new UserNotFoundException(doctorDto.userId()));

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException(id));

        boolean crmNotFound = !doctorRepository.existsByCrm(doctorDto.crm())
                && !doctor.getCrm().equals(doctorDto.crm());

        if (crmNotFound) {
            throw new DoctorNotFoundException(doctorDto.id());
        }
        doctor.setUser(user);
        doctor.setCrm(doctorDto.crm());
        doctor.setSpeciality(doctorDto.speciality());
        doctor.setPhoneNumber(doctorDto.phoneNumber());

            return doctorMapping.toDto(doctorRepository.save(doctor));
}
    public void delete(UUID id) {
        boolean exists = doctorRepository.existsById(id);
        if(!exists) {
            throw new DoctorNotFoundException(id);
        }
        doctorRepository.deleteById(id);
}

    public DoctorDto findById(UUID id) {
    return doctorRepository.findById(id)
            .map(doctorMapping::toDto)
            .orElseThrow(() -> new DoctorNotFoundException(id));
}

    public List<DoctorDto> findByDoctorName(String name) {
    return doctorRepository
            .findByUserNameContainingIgnoreCase(name)
            .stream()
            .map(doctorMapping::toDto)
            .toList();
}

    public DoctorDto findByCrm(Integer crm) {
    return doctorRepository.findByCrmContainingIgnoreCase(crm)
            .map(doctorMapping::toDto)
            .orElseThrow(() -> new CrmNotFound(crm));
}

}
