package com.CodeCraft.bwtt01.repository;

import com.CodeCraft.bwtt01.model.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<UUID, UserDetails> userStorage = new HashMap<>();

    public UserDetails save(UserDetails userDetails) {
        userStorage.put(userDetails.getId(), userDetails);
        return userDetails;
    }

    public Optional<UserDetails> findById(UUID id) {
        return Optional.ofNullable(userStorage.get(id));
    }

    public List<UserDetails> findAll() {
        return new ArrayList<>(userStorage.values());
    }

    public UserDetails update(UUID id, UserDetails updatedUser) {
        UserDetails existingUser = userStorage.get(id);
        if (existingUser == null)
            throw new RuntimeException("User id not found : "+id);
        if (updatedUser.getName() != null)
            existingUser.setName(updatedUser.getName());
        if (updatedUser.getEmail() != null)
            existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getAge() != 0) //not set limits for age -ve and +ve
            existingUser.setAge(updatedUser.getAge());

        userStorage.put(id, existingUser);

        return existingUser;
    }

    public void deleteById(UUID id){
        userStorage.remove(id);
    }
}
