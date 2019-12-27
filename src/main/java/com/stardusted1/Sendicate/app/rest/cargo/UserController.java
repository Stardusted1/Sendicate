package com.stardusted1.Sendicate.app.rest.cargo;

import com.stardusted1.Sendicate.app.core.users.Customer;
import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.core.users.User;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController    // This means that this class is a Controller
@RequestMapping(path="api/users")
public class UserController {
    @Autowired
    System system;

    @GetMapping("/deliveryman/{id}")
    User getDeliveryman(@PathVariable String id){
        Optional<Deliveryman> usr  = system.deliverymanRepository.findById(id);
        User user = usr.orElse(null);
        return user;
    }

    @GetMapping("/receiver/{id}")
    User getReceiver(@PathVariable String id){
        Optional<Receiver> usr  = system.receiverRepository.findById(id);
        return (User) usr.orElse(null);
    }
}
