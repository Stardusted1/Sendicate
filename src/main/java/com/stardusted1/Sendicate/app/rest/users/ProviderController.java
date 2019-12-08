package com.stardusted1.Sendicate.app.rest.users;

import com.stardusted1.Sendicate.app.core.repositories.ProviderRepository;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("api/users/providers")
public class ProviderController {

	@Autowired
	ProviderRepository providerRepository;

	@PostMapping("{id}")
	String updateUser(@PathVariable("id") String id,
					  @RequestBody String body){
		var user = providerRepository.findById(id);
		if(user.isEmpty()){
			return "no";
		}else {
			Provider provider = user.get();
			Map<String,String> request = System.parseBody(body);
			provider.getEmails().clear();
			provider.addEmail(URLDecoder.decode(request.get("emailaddr"), Charset.defaultCharset()));
			// TODO: 08.12.2019 IMPLEMENT:add multiple mail
			provider.getPhones().clear();
			provider.addPhone(request.get("phone"));
			// TODO: 08.12.2019  IMPLEMENT: add multiple phone
			provider.setName(request.get("username"));
			provider.getAddress().clear();
			provider.addAddress(URLDecoder.decode(request.get("addressWeb"),Charset.defaultCharset()));
			// TODO: 08.12.2019 implement: multiple address
			provider.setSiteAddress(URLDecoder.decode(request.get("addressWeb"),Charset.defaultCharset()));
			provider.setDescription(request.get("description"));
			providerRepository.save(provider);
		}
		return "ok";
	}
	@PostMapping(path = "{id}/delete/{token}")
	String deleteReceiver(@PathVariable("id") String id,
						  @PathVariable("token") String token){
		var user = providerRepository.findById(id);
		if(user.isEmpty()){
			return "no";
		}else {
			Provider provider = user.get();
			if(provider.getToken().equals(token)){
				//receiverRepository.delete(receiver);
			}
		}
		SecurityContextHolder.clearContext();
		return "deleted";
	}

    @PutMapping()
    public String putMethod(/*@PathVariable int id*/) {
        return "this is put request";
        /*TODO:IMPLEMENT put method */
    }

    @DeleteMapping()
    public String DeleteMethod(/*@PathVariable int id*/) {
        return "this is delete! request";
        /*TODO: implement delete method */
    }

    @PatchMapping()
    public String PatchMethod(/*@PathVariable int id*/) {
        return "this is patch!2 request";
        /*TODO: implement patch method */
    }

}
