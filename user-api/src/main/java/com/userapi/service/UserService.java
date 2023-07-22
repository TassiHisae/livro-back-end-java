package com.userapi.service;

import com.userapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAll();

    UserDTO findById(Long userId);

    UserDTO save(UserDTO userDTO);

    UserDTO delete(Long userId);

    UserDTO findByCpfAndKey(String cpf, String key);

    List<UserDTO> queryByName(String name);
}
