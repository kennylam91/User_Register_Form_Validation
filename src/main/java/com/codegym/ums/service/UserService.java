package com.codegym.ums.service;

import com.codegym.ums.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void save(User user);

    Page<User> findAll(Pageable pageable);
}
