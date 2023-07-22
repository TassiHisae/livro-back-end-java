package com.userapi.controller;

import com.exception.UserNotFoundException;
import com.userapi.dto.UserDTO;
import com.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public static List<UserDTO> users = new ArrayList<>();

    @PostConstruct
    public void iniciateList() {
        UserDTO userDTO = new UserDTO()
                .setName("Eduardo")
                .setCpf("123")
                .setAddress("Rua a")
                .setEmail("eduardo@email.com")
                .setPhone("1234-3454")
                .setRegisterDate(new Date());

        UserDTO userDTO2 = new UserDTO()
                .setName("Luiz")
                .setCpf("456")
                .setAddress("Rua b")
                .setEmail("luiz@email.com")
                .setPhone("1234-3454")
                .setRegisterDate(new Date());

        UserDTO userDTO3 = new UserDTO()
                .setName("Bruna")
                .setCpf("789")
                .setAddress("Rua c")
                .setEmail("bruna@email.com")
                .setPhone("1234-3454")
                .setRegisterDate(new Date());

        users.add(userDTO);
        users.add(userDTO2);
        users.add(userDTO3);
    }

    @GetMapping("/")
    public String getMessage() {
        return "Spring boot is working!!";
    }

    @GetMapping("/user/")
    public List<UserDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDTO findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping("/user/cpf/{cpf}")
    public UserDTO findByCpf(@PathVariable("cpf") String cpf, @RequestParam(name = "key", required = true) String key) {
        UserDTO user = userService.findByCpfAndKey(cpf, key);

        if (Objects.nonNull(user)) {
            return user;
        }

        throw new UserNotFoundException();
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(@RequestParam(name = "name", required = true) String name) {
        return userService.queryByName(name);
    }

    @PostMapping("/user")
    public UserDTO newUser(@RequestBody UserDTO user) {
        return userService.save(user);
    }

    @DeleteMapping("/user/{id}")
    public UserDTO delete(@PathVariable("id") Long id) {
        return userService.delete(id);
    }
}
