package dev.clinican.mapping;

import dev.clinican.dto.DoctorDto;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.TbUser;
import dev.clinican.repository.TbUserRepository;

public class DoctorMapping {

    private final TbUserRepository tbUserRepository;

    public DoctorMapping(TbUserRepository tbUserRepository) {
        this.tbUserRepository = tbUserRepository;
    }


    public Doctor toEntity(DoctorDto doctorDto) {
        TbUser user = tbUserRepository.findById(doctorDto.userId())
                .orElseThrow(()-> new RuntimeException("User not found"));

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setCrm(doctorDto.crm());
        doctor.setSpeciality(doctorDto.speciality());
        doctor.setPhoneNumber(doctorDto.phoneNumber());

        return doctor;
    }

    public DoctorDto toDto(Doctor doctor) {
        return new DoctorDto(
                doctor.getId(),
                doctor.getUser().getId(),
                doctor.getCrm(),
                doctor.getSpeciality(),
                doctor.getPhoneNumber()
        );
    }
}
