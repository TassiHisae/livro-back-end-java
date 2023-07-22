package com.userapi.repository;

import com.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByCpfAndKey(String cpf, String key);

    List<User> queryByNameLike(String name);
}
