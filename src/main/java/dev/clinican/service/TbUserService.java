package dev.clinican.service;


import dev.clinican.dto.TbUserDto;
import dev.clinican.dto.TbUserLoginDto;
import dev.clinican.entity.TbUser;
import dev.clinican.exception.UserAlreadyExistsException;
import dev.clinican.exception.UserNotFoundException;
import dev.clinican.mapping.TbUserMapping;
import dev.clinican.repository.TbUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service

public class TbUserService {

    private final TbUserRepository tbUserRepository;
    private final TbUserMapping tbUserMapping;
    private final PasswordEncoder passwordEncoder;

    public TbUserService(TbUserRepository repository,
                         TbUserMapping tbUserMapping,
                         PasswordEncoder passwordEncoder) {
        this.tbUserRepository = repository;
        this.tbUserMapping = tbUserMapping;
        this.passwordEncoder = passwordEncoder;
    }



    public TbUserDto create(TbUserLoginDto user) {

        if(tbUserRepository.existsByEmail(user.email())) {
            throw new UserAlreadyExistsException(user.email());
        }

        TbUser tbUser = tbUserMapping.toEntity(user);
        tbUser.setPassword(passwordEncoder.encode(user.password()));
        TbUser saveTbUser = tbUserRepository.save(tbUser);
        return tbUserMapping.toDto(saveTbUser);

    }

    public TbUserDto update(UUID id, TbUserLoginDto user) {
        TbUser tbUser = tbUserRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));

        boolean emailConflict = tbUserRepository.existsByEmail(user.email())
                && !tbUser.getEmail().equals(user.email());

        if (emailConflict) {
            throw new UserAlreadyExistsException(user.email());
        }
        tbUser.setName(user.name());
        tbUser.setEmail(user.email());
        tbUser.setPassword(passwordEncoder.encode(user.password()));
        tbUser.setRoleType(user.role());
        tbUser.setCreatedAt(user.createdAt());
        tbUser.setActive(user.active());
        return tbUserMapping.toDto((tbUserRepository.save(tbUser)));

    }

    public void delete(UUID id) {
        boolean exists = tbUserRepository.existsById(id);
        if(!exists) {
            throw new UserNotFoundException(id);
        }
        tbUserRepository.deleteById(id);
    }

    public TbUserDto findById(UUID id) {
        return tbUserRepository.findById(id)
                .map(tbUserMapping::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<TbUserDto> findByName(String name) {
        return tbUserRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(tbUserMapping::toDto)
                .toList();
    }
}
