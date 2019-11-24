package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.users.NewUser;
import com.stardusted1.Sendicate.app.core.users.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class PageController {
//    Optional user= (Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Optional optional= (Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    @GetMapping("/")
    public String  Index(Model model) {
        return "index";
    }

    @GetMapping("/user")
    public String  user(Model model) {
        User user= (User) optional.get();
        return "user";
    }

    @GetMapping("/login")
    public String  login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String  register(Model model) {
        return "register";
    }
    // TODO: 21.11.2019 404 страница

    @GetMapping("/forgot_pass")
    public String  forgot(Model model) {
        return "forgot_pass";
    }




}
