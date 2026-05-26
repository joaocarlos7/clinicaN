package dev.clinican.service;


import dev.clinican.dto.TbUserDto;
import dev.clinican.dto.TbUserLoginDto;
import dev.clinican.entity.Doctor;
import dev.clinican.entity.TbUser;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service

public class TbUserService {

    private final TbUserRepository tbUserRepository;
    public TbUserService(TbUserRepository repository) {
        this.tbUserRepository = repository;
    }

    // Entity to DTO (Exit)
    private TbUserDto toDto(TbUser user) {
        return new TbUserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoleType(),
                user.getCreatedAt(),
                user.getActive());}

    // DTO to Entity (Entry)
    private TbUser toEntity(TbUserLoginDto user) {
        TbUser tbUser = new TbUser();
        tbUser.setName(user.name());
        tbUser.setEmail(user.email());
        tbUser.setPassword(user.password());
        tbUser.setRoleType(user.role());
        tbUser.setCreatedAt(user.createdAt());
        tbUser.setActive(user.active());

        return tbUser;

    }


    // Public Methods
    public TbUserDto create(TbUserLoginDto user) {
        TbUser tbUser = toEntity(user);
        TbUser saveTbUser = tbUserRepository.save(tbUser);

        return toDto(saveTbUser);
    }
    public TbUserDto update(UUID id, TbUserLoginDto user) {
        TbUser tbUser = tbUserRepository.findById(id).
                orElseThrow(()-> new RuntimeException("User not found" + id));
        tbUser.setName(user.name());
        tbUser.setEmail(user.email());
        tbUser.setPassword(user.password());
        tbUser.setRoleType(user.role());
        tbUser.setCreatedAt(user.createdAt());
        tbUser.setActive(user.active());

        return toDto(tbUserRepository.save(tbUser));

    }
    public void delete(UUID id) {
        tbUserRepository.deleteById(id);
    }
    public TbUserDto findById(UUID id) {
        return tbUserRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }
    public List<TbUserDto> findByName(String name) {
        return tbUserRepository.findByNameContainingIgnoreCase(name)
                .stream() // Take the list one by one
                .map(this::toDto)// Convert in Dto
                .toList(); // List
    }
}
