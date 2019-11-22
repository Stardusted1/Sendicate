package com.stardusted1.Sendicate.app.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String  Index(Model model) {
        return "index";
    }
//    @GetMapping("/index")
//    public String  index(Model model) {
//        return "index";
//    }

    @GetMapping("/login")
    public String  login(Model model) {
        return "login1";
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
