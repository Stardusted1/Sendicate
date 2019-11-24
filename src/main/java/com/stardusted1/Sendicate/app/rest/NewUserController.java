package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.users.NewUser;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/new_user")
public class NewUserController {
    @Autowired
    System system;
    @GetMapping()
    public Iterable<NewUser> Index() {
//        System system = new System();
        return system.newUserRepository.findAll();
    }
}
