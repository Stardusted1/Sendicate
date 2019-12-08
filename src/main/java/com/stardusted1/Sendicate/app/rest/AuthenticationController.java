package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.repositories.DeliverymanRepository;
import com.stardusted1.Sendicate.app.core.repositories.NewUserRepository;
import com.stardusted1.Sendicate.app.core.repositories.ProviderRepository;
import com.stardusted1.Sendicate.app.core.repositories.ReceiverRepository;
import com.stardusted1.Sendicate.app.core.users.*;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/login")
public class AuthenticationController {
	@Autowired
	private ReceiverRepository receiverRepository;
	@Autowired
	private DeliverymanRepository deliverymanRepository;
	@Autowired
	private ProviderRepository providerRepository;
	@Autowired
	private NewUserRepository newUserRepository;

	private System system = new System();

	@PostMapping(path = "/forgot_password")
	String forgot_password(
			@RequestParam String name) {
		try {
			system.renewPassword(name);
			return "Ok";
		} catch (Exception e) {
			return "Something went wrong";
		}
	}

	@PostMapping()
	String login(@RequestParam String login,
				 @RequestParam String pass) {
		try {
			return system.AuthenticateUser(login, pass);
		} catch (Exception e) {
			return "Something went wrong";
		}
	}

	@PostMapping("register")
	String register(@RequestBody String body) {
		var usr = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usr.getClass().getName().equals("java.lang.String")) {
			if (usr.equals("anonymousUser")) {
				return "index";
			}
		} else {
			Optional optional = (Optional) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			User user = (User) optional.get();
			if (user.getRole().equals("USER")) {
				Map<String, String> request = System.parseBody(body);

				String role = request.get("role");
				String name = request.get("username");

				if (Integer.parseInt(role) == 1) {
					Provider provider = new Provider();
					provider.setId(user.getId());
					provider.setPassword(user.getPassword());
					provider.setName(name);
					provider.addEmail(((NewUser) user).getEmail());
					provider.setDateOfRegistration(new Date());
					provider.setPictureUrl(user.getPictureUrl());
					provider.newToken();
					providerRepository.save(provider);
					newUserRepository.delete((NewUser) user);
				} else if (Integer.parseInt(role) == 2) {
					Deliveryman deliveryman = new Deliveryman();
					deliveryman.setId(user.getId());
					deliveryman.setPassword(user.getPassword());
					deliveryman.setName(name);
					deliveryman.addEmail(((NewUser) user).getEmail());
					deliveryman.setDateOfRegistration(new Date());
					deliveryman.setPictureUrl(user.getPictureUrl());
					deliveryman.newToken();
					deliverymanRepository.save(deliveryman);
					newUserRepository.delete((NewUser) user);
				} else if (Integer.parseInt(role) == 3) {
					Receiver receiver = new Receiver();
					receiver.setId(user.getId());
					receiver.setPassword(user.getPassword());
					receiver.setName(name);
					receiver.addEmail(((NewUser) user).getEmail());
					receiver.setDateOfRegistration(new Date());
					receiver.setPictureUrl(user.getPictureUrl());
					receiver.newToken();
					receiverRepository.save(receiver);
					newUserRepository.delete((NewUser) user);
				}
				SecurityContextHolder.clearContext();
			}
		}
		return "index";
	}
}
