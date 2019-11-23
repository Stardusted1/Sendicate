package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.cargo.Supply;
import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.service.EmailNotifier;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mailing")
public class MailController {
	System system  = new System();

	@PostMapping
	public String SendMail(@RequestParam(value = "email", defaultValue = "refineanswer@gmail.com") String email,
						   @RequestParam(value = "topic", defaultValue = "TestSpring") String topic,
						   @RequestBody String text) {

		system.emailNotifier.sendMail(email,topic,text);
		return "sent";

	}
}
