package dev.clinican.repository;

import dev.clinican.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TbUserRepository extends JpaRepository<TbUser, UUID> {

    List<TbUser> findByNameContainingIgnoreCase(String name);
}
