package dev.clinican.mapping;

import dev.clinican.dto.TbUserDto;
import dev.clinican.dto.TbUserLoginDto;
import dev.clinican.entity.TbUser;
import org.springframework.stereotype.Component;


@Component
public class TbUserMapping {

    public TbUserDto toDto(TbUser user) {
        return new TbUserDto(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoleType(),
                user.getCreatedAt(),
                user.getActive());}

    public TbUser toEntity(TbUserLoginDto user) {
        TbUser tbUser = new TbUser();
        tbUser.setName(user.name());
        tbUser.setEmail(user.email());
        tbUser.setPassword(user.password());
        tbUser.setRoleType(user.role());
        tbUser.setCreatedAt(user.createdAt());
        tbUser.setActive(user.active());

        return tbUser;

    }



}

