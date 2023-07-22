package com.userapi.service.impl;

import com.userapi.dto.DTOConverter;
import com.userapi.dto.UserDTO;
import com.userapi.model.User;
import com.userapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    public static User getUser(Long id, String name, String cpf){
        return new User()
                .setId(id)
                .setName(name)
                .setCpf(cpf)
                .setAddress("Address")
                .setPhone("5432");
    }

    @Test
    public void testListAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(getUser(1L, "User name", "123"));
        users.add(getUser(2L, "User name 2", "321"));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userDTOList = userService.getAll();
        Assertions.assertEquals(2, userDTOList.size());
    }

    @Test
    public void testSaveUser(){
        User user = getUser(1L, "User name", "123");
        UserDTO userDTO = DTOConverter.convert(user);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        UserDTO userReturn = userService.save(userDTO);
        Assertions.assertEquals("User name", userReturn.getName());
        Assertions.assertEquals("123", userReturn.getCpf());
    }

//    @Test
//    public void testEditUser(){
//        User user = getUser(1L, "User name", "123");
//
//        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
//
//        UserDTO userDTO = DTOConverter.convert(user)
//                .setAddress("new address")
//                .setPhone("1234");
//
//        UserDTO userReturn = userService.save(userDTO);
//        Assertions.assertEquals("new address", userReturn.getAddress());
//        Assertions.assertEquals("1234", userReturn.getPhone());
//    }
}
