package com.devjasj.users.services.impl;

import com.devjasj.users.domain.dto.UserDTO;
import com.devjasj.users.domain.entity.User;
import com.devjasj.users.repository.UserRepository;
import com.devjasj.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final ModelMapper modelMapper;

    @Override
    public UserDTO saveUser(UserDTO user) {
        User userEntity = modelMapper
                .map(user, User.class);

        return modelMapper
                .map(userRepository.save(userEntity), UserDTO.class);
    }

    @Override
    public UserDTO findUserById(Long id) {
        User userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not exists", id)));

        return modelMapper
                .map(userEntity, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userEntity -> modelMapper
                        .map(userEntity, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUserById(Long id, UserDTO user) {
        User userToUpdate = userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not exists", id)));

        userToUpdate.setAge(user.getAge());
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());

        User userEntityUpdated = userRepository
                .save(userToUpdate);

        return modelMapper
                .map(userEntityUpdated, UserDTO.class);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %d not exists", id)));

        userRepository.deleteById(id);
    }
}
