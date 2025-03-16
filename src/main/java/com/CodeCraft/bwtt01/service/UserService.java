package com.CodeCraft.bwtt01.service;

import com.CodeCraft.bwtt01.dto.UserDetailsDto;
import com.CodeCraft.bwtt01.model.UserDetails;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDetailsDto createUser(UserDetailsDto userDetails);
    UserDetailsDto getUserBYId(UUID id);
    List <UserDetailsDto> getAllUsers();
    UserDetailsDto updateUser(UUID id, UserDetailsDto userDetails);
    void deleteUser (UUID id);
}