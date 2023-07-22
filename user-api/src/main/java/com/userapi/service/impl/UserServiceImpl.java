package com.userapi.service.impl;

import com.userapi.dto.DTOConverter;
import com.userapi.dto.UserDTO;
import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import com.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(DTOConverter::convert).orElse(null);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        userDTO.setKey(UUID.randomUUID().toString());
        return DTOConverter.convert(userRepository.save(User.convert(userDTO)));
    }

    @Override
    public UserDTO delete(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(value -> userRepository.delete(value));
        return null;
    }

    @Override
    public UserDTO findByCpfAndKey(String cpf, String key) {
        User user = userRepository.findByCpfAndKey(cpf, key);
        if (Objects.nonNull(user)) {
            return DTOConverter.convert(user);
        }
        return null;
    }

    @Override
    public List<UserDTO> queryByName(String name) {
        return userRepository.queryByNameLike(name)
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }
}
