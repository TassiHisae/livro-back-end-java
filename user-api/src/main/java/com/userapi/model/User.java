package com.userapi.model;

import com.userapi.dto.UserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String phone;
    private Date registerDate;
    private String key;

    public static User convert(UserDTO dto) {
        return new User()
                .setName(dto.getName())
                .setAddress(dto.getAddress())
                .setCpf(dto.getCpf())
                .setEmail(dto.getEmail())
                .setPhone(dto.getPhone())
                .setRegisterDate(dto.getRegisterDate())
                .setKey(dto.getKey());
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public User setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public User setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }

    public String getKey() {
        return key;
    }

    public User setKey(String key) {
        this.key = key;
        return this;
    }
}
