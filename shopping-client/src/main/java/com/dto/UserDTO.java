package com.dto;

import java.util.Date;

public class UserDTO {

    private String name;
    private String cpf;
    private String address;
    private String email;
    private String phone;
    private Date registerDate;

    public String getName() {
        return name;
    }

    public UserDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public UserDTO setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public UserDTO setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
        return this;
    }
}
