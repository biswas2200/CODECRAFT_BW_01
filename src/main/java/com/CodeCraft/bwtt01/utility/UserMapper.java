package com.CodeCraft.bwtt01.utility;

import com.CodeCraft.bwtt01.dto.UserDetailsDto;
import com.CodeCraft.bwtt01.model.UserDetails;

public class UserMapper {
    public static UserDetails toEntity(UserDetailsDto dto) {
        UserDetails user = new UserDetails();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return user;
    }

    public static UserDetailsDto toDto(UserDetails user){
        UserDetailsDto dto = new UserDetailsDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }
}
