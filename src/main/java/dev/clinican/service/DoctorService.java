package dev.clinican.service;


import dev.clinican.entity.TbUser;
import dev.clinican.repository.DoctorRepository;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final TbUserRepository tbUserRepository;

    public DoctorService(DoctorRepository doctorRepository, TbUserRepository tbUserRepository) {
        this.doctorRepository = doctorRepository;
        this.tbUserRepository = tbUserRepository;

    }

    

}
