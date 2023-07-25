package com.userapi.controller;

import com.userapi.dto.DTOConverter;
import com.userapi.dto.UserDTO;
import com.userapi.service.UserService;
import com.userapi.service.impl.UserServiceImplTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @Test
    public void testListUsers() throws Exception {
        List<UserDTO> users = new ArrayList<>();
        users.add(DTOConverter.convert(UserServiceImplTest.getUser(1L, "name 1", "123")));

        Mockito.when(userService.getAll()).thenReturn(users);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/user/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String resp = result.getResponse().getContentAsString();
        Assertions.assertEquals("[{\"name\":\"name 1\"," +
                "\"cpf\":\"123\",\"address\":\"Address\",\"email\":null," +
                "\"phone\":\"5432\",\"registerDate\":null,\"key\":null}]", resp);
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
}

