package dev.clinican.service;


import dev.clinican.dto.TbUserDto;
import dev.clinican.dto.TbUserLoginDto;
import dev.clinican.entity.TbUser;
import dev.clinican.exception.UserAlreadyExistsException;
import dev.clinican.mapping.TbUserMapping;
import dev.clinican.repository.TbUserRepository;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;
import java.util.UUID;


@Service

public class TbUserService {

    private final TbUserRepository tbUserRepository;
    private final TbUserMapping tbUserMapping;
    public TbUserService(TbUserRepository repository,
                         TbUserMapping tbUserMapping) {
        this.tbUserRepository = repository;
        this.tbUserMapping = tbUserMapping;
    }



    // Public Methods

    // Create
    public TbUserDto create(TbUserLoginDto user) {
        if(tbUserRepository.existsByEmail(user.email())) {
            throw new UserAlreadyExistsException(user.email());
        } try {

        TbUser tbUser = tbUserMapping.toEntity(user);
        TbUser saveTbUser = tbUserRepository.save(tbUser);
            return tbUserMapping.toDto(saveTbUser);

    } catch (RuntimeErrorException e) {
        throw new UserAlreadyExistsException(user.email());
        }
    }

    // Update
    public TbUserDto update(UUID id, TbUserLoginDto user) {
        TbUser tbUser = tbUserRepository.findById(id).
                orElseThrow(()-> new RuntimeException("User not found" + id));
        tbUser.setName(user.name());
        tbUser.setEmail(user.email());
        tbUser.setPassword(user.password());
        tbUser.setRoleType(user.role());
        tbUser.setCreatedAt(user.createdAt());
        tbUser.setActive(user.active());

        if (tbUserRepository.existsByEmailAndIdNot(user.email(), id)) {
            throw new UserAlreadyExistsException(user.email());
        } try {
        return tbUserMapping.toDto((tbUserRepository.save(tbUser)));

    } catch (RuntimeErrorException e) {
        throw new UserAlreadyExistsException(user.email());}
    }

    // Delete
    public void delete(UUID id) {
        tbUserRepository.deleteById(id);
    }

    // Find by Id
    public TbUserDto findById(UUID id) {
        return tbUserRepository.findById(id)
                .map(tbUserMapping::toDto)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
    }

    // Find by Name
    public List<TbUserDto> findByName(String name) {
        return tbUserRepository.findByNameContainingIgnoreCase(name)
                .stream() // Take the list one by one
                .map(tbUserMapping::toDto)// Convert in Dto
                .toList(); // List
    }
}
