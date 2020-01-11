package com.stardusted1.Sendicate.app.rest.users;

import com.stardusted1.Sendicate.app.core.repositories.DeliverymanRepository;
import com.stardusted1.Sendicate.app.core.users.Deliveryman;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.service.System;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.HttpCookie;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users/deliverymans")
public class DeliverymansController {

	@Autowired
	DeliverymanRepository deliverymanRepository;

	@PostMapping("{id}")
	Map<String, Object> updateUser(@PathVariable("id") String id,
								   @RequestBody String body){
		var user = deliverymanRepository.findById(id);
		Map<String, Object> request = new BasicJsonParser().parseMap(body);
		if(user.isEmpty()){
			return null;
		}else {
			boolean isChanged = false;
			Deliveryman deliveryman = user.get();
			Deliveryman deliveryman1 = (Deliveryman) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (request.get("address") != null) {
				deliveryman.addAddress(URLDecoder.decode((String) request.get("address"),Charset.defaultCharset()));
				deliveryman1.addAddress(URLDecoder.decode((String) request.get("address"),Charset.defaultCharset()));
				isChanged = true;
			}
			if (request.get("description") != null) {
				deliveryman.addEmail((String) request.get("emailaddr"));
				deliveryman1.addEmail((String) request.get("emailaddr"));
				deliveryman.setDescription((String) request.get("description"));

				isChanged = true;
			}
			if (request.get("addressWeb") != null) {
				deliveryman.setSiteAddress(URLDecoder.decode((String) request.get("addressWeb"),Charset.defaultCharset()));
				deliveryman1.setSiteAddress(URLDecoder.decode((String) request.get("addressWeb"),Charset.defaultCharset()));

				isChanged = true;
			}
			if (request.get("emailaddr") != null) {
				deliveryman.addEmail((String) request.get("emailaddr"));
				deliveryman1.addEmail((String) request.get("emailaddr"));

				isChanged = true;
			}
			if (request.get("phone") != null) {
				deliveryman.addPhone((String) request.get("phone"));
				deliveryman1.addPhone((String) request.get("phone"));
				isChanged = true;
			}
			if (request.get("name") != null) {
				isChanged = true;
				deliveryman.setName((String) request.get("name"));
				deliveryman1.setName((String) request.get("name"));
			}
			if (isChanged)
				deliverymanRepository.save(deliveryman);
		}

		return request;
	}

	@PostMapping("{id}/new_token/{old_token}")
	Map<String, String> updateToken(@PathVariable(name = "id") Deliveryman deliveryman,
									@PathVariable(name = "old_token") String token){
		if(deliveryman.getToken().equals(token)){
			deliveryman.newToken();
			deliverymanRepository.save(deliveryman);
			((Deliveryman)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setToken(deliveryman.getToken());
			HashMap<String,String> response = new HashMap<>();
			response.put("token",deliveryman.getToken());
			return response;
		}
		return null;
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
