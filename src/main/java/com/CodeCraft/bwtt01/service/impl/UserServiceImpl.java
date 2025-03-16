package com.CodeCraft.bwtt01.service.impl;

import com.CodeCraft.bwtt01.dto.UserDetailsDto;
import com.CodeCraft.bwtt01.exception.UserNotFoundException;
import com.CodeCraft.bwtt01.model.UserDetails;
import com.CodeCraft.bwtt01.repository.UserRepository;
import com.CodeCraft.bwtt01.service.UserService;
import com.CodeCraft.bwtt01.utility.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsDto createUser(UserDetailsDto dto) {
        UserDetails user = UserMapper.toEntity(dto);
        if (user.getId() == null)
            user.setId(UUID.randomUUID());
        UserDetails savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    @Override
    public UserDetailsDto getUserBYId(UUID id) {
        UserDetails user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id not found " + id));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserDetailsDto> getAllUsers() {
        List<UserDetails> user = userRepository.findAll();
        return user.stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetailsDto updateUser(UUID id, UserDetailsDto userDto) {
        UserDetails updateUserDetails = UserMapper.toEntity(userDto);
        UserDetails updatedUser = userRepository.update(id, updateUserDetails);
        return UserMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id not found " + id));
        userRepository.deleteById(id);
    }
}
