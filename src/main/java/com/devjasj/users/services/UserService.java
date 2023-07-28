package com.devjasj.users.services;

import com.devjasj.users.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    public UserDTO saveUser(UserDTO user);

    public UserDTO findUserById(Long id);

    public List<UserDTO> findAllUsers();

    public UserDTO updateUserById(Long id, UserDTO user);

    public void deleteUserById(Long id);
}
