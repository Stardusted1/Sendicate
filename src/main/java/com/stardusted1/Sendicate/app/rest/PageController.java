package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.users.*;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class PageController {
//    Optional user= (Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


	@GetMapping("/")
	public String Index(Model model) {
		var usr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usr.getClass().getName().equals("java.lang.String")) {
			if (usr.equals("anonymousUser")) {
				model.addAttribute("usr", usr);
				model.addAttribute("anon", true);
			} else {
				model.addAttribute("usr", usr);
			}
		} else {
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getSimpleName().equals("Optional")) {
				Optional optional = (Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				User user = (User) optional.get();
				if (user.getRole().equals("USER")) {
					model.addAttribute("newUser", true);
				} else {
					model.addAttribute("newUser", false);
				}
				model.addAttribute("anon", false);
				model.addAttribute("pictureUrl", user.getPictureUrl());
			} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getSimpleName().equals("Provider")) {
				Provider provider = (Provider) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				model.addAttribute("newUser", false);
				model.addAttribute("anon", false);
				model.addAttribute("pictureUrl", provider.getPictureUrl());
			} else if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().getSimpleName().equals("Deliveryman")) {
				Deliveryman deliveryman = (Deliveryman) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				model.addAttribute("newUser", false);
				model.addAttribute("anon", false);
				model.addAttribute("pictureUrl", deliveryman.getPictureUrl());
			}

		}
		return "index";
	}

	@GetMapping("/main")
	public String user(Model model) {
		var customer = System.recognizeUser();
		if (customer == null) {
			return "register";
		}
		model.addAttribute("customer",customer);
		return "main";
	}

	@GetMapping("/profile")
	public String profile(Model model) {
		var sContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Customer user = null;
		if (sContext.getClass().getSimpleName().equals("Optional")) {
			user = (Customer) ((Optional) sContext).get();
		} else {
			user = (Customer) sContext;
		}
		if (user.getRole().equals("USER")) {
			model.addAttribute("usr", user);
			return "register";
		}
		if (user.getRole().equals("BCUSTOMER")) {

			model.addAttribute("BCUSTOMER", true);
			model.addAttribute("address", ((BusinessCustomer) user).getAddress().getFirst());
			model.addAttribute("addressWeb", ((BusinessCustomer) user).getSiteAddress());
			model.addAttribute("desc", ((BusinessCustomer) user).getDescription());
			model.addAttribute("usr", (BusinessCustomer) user);
		} else if (user.getRole().equals("CUSTOMER")) {

			model.addAttribute("BCUSTOMER", false);
			model.addAttribute("usr", (Customer) user);
		}
		model.addAttribute("type", user.getClass().getSimpleName().toLowerCase() + "s");
		return "profile";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		Optional optional = (Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) optional.get();
		model.addAttribute("usr", user);
		return "register";
	}

	@GetMapping("compile")
	public String compile(Model model){
		return "compiler";
	}
	// TODO: 21.11.2019 404 страница


}
