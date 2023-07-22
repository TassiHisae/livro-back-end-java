package com.userapi.dto;

import com.userapi.model.User;

public class DTOConverter {

    public static UserDTO convert(User user) {
        return new UserDTO()
                .setName(user.getName())
                .setAddress(user.getAddress())
                .setCpf(user.getCpf())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone())
                .setRegisterDate(user.getRegisterDate())
                .setKey(user.getKey());
    }

}
