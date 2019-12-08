package com.stardusted1.Sendicate.app.rest.users;

import com.stardusted1.Sendicate.app.core.repositories.DeliverymanRepository;
import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Map;

@RestController
@RequestMapping("api/users/deliverymans")
public class DeliverymansController {

	@Autowired
	DeliverymanRepository deliverymanRepository;

	@PostMapping("{id}")
	String updateUser(@PathVariable("id") String id,
					  @RequestBody String body){
		var user = deliverymanRepository.findById(id);
		if(user.isEmpty()){
			return "no";
		}else {
			Deliveryman deliveryman = user.get();
			Map<String,String> request = System.parseBody(body);
			deliveryman.getEmails().clear();
			deliveryman.addEmail(URLDecoder.decode(request.get("emailaddr"), Charset.defaultCharset()));
			// TODO: 08.12.2019 IMPLEMENT:add multiple mail
			deliveryman.getPhones().clear();
			deliveryman.addPhone(request.get("phone"));
			// TODO: 08.12.2019  IMPLEMENT: add multiple phone
			deliveryman.setName(request.get("username"));
			deliveryman.getAddress().clear();
			deliveryman.addAddress(URLDecoder.decode(request.get("addressWeb"),Charset.defaultCharset()));
			// TODO: 08.12.2019 implement: multiple address
			deliveryman.setSiteAddress(URLDecoder.decode(request.get("addressWeb"),Charset.defaultCharset()));
			deliveryman.setDescription(request.get("description"));
			deliverymanRepository.save(deliveryman);
		}
		return "ok";
	}
	@PostMapping(path = "{id}/delete/{token}")
	String deleteReceiver(@PathVariable("id") String id,
						  @PathVariable("token") String token){
		var user = deliverymanRepository.findById(id);
		if(user.isEmpty()){
			return "no";
		}else {
			Deliveryman deliveryman = user.get();
			if(deliveryman.getToken().equals(token)){
				//receiverRepository.delete(receiver);
			}
		}
		SecurityContextHolder.clearContext();
		return "deleted";
	}


}
