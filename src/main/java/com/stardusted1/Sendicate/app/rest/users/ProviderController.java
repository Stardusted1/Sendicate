package com.stardusted1.Sendicate.app.rest.users;

import com.stardusted1.Sendicate.app.core.repositories.ProviderRepository;
import com.stardusted1.Sendicate.app.core.users.Provider;
import com.stardusted1.Sendicate.app.core.users.Receiver;
import com.stardusted1.Sendicate.app.service.System;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.BasicJsonParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users/providers")
public class ProviderController {

	@Autowired
	ProviderRepository providerRepository;

	@PostMapping("{id}")
	Map<String,Object> updateUser(@PathVariable("id") String id,
					  @RequestBody String body){
		var user = providerRepository.findById(id);
		Map<String, Object> request = new BasicJsonParser().parseMap(body);
		if(user.isEmpty()){
			return null;
		}else {
			boolean isChanged = false;
			Provider provider = user.get();
			Provider provider1 = (Provider) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (request.get("address") != null) {
				provider.addAddress(URLDecoder.decode((String) request.get("address"),Charset.defaultCharset()));
				provider1.addAddress(URLDecoder.decode((String) request.get("address"),Charset.defaultCharset()));
				isChanged = true;
			}
			if (request.get("description") != null) {
				provider.addEmail((String) request.get("emailaddr"));
				provider1.addEmail((String) request.get("emailaddr"));
				provider.setDescription((String) request.get("description"));

				isChanged = true;
			}
			if (request.get("addressWeb") != null) {
				provider.setSiteAddress(URLDecoder.decode((String) request.get("addressWeb"),Charset.defaultCharset()));
				provider1.setSiteAddress(URLDecoder.decode((String) request.get("addressWeb"),Charset.defaultCharset()));

				isChanged = true;
			}
			if (request.get("emailaddr") != null) {
				provider.addEmail((String) request.get("emailaddr"));
				provider1.addEmail((String) request.get("emailaddr"));

				isChanged = true;
			}
			if (request.get("phone") != null) {
				provider.addPhone((String) request.get("phone"));
				provider1.addPhone((String) request.get("phone"));
				isChanged = true;
			}
			if (request.get("name") != null) {
				isChanged = true;
				provider.setName((String) request.get("name"));
				provider1.setName((String) request.get("name"));
			}
			if (isChanged)
				providerRepository.save(provider);
		}

		return request;
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
				SecurityContextHolder.clearContext();
				//providerRepository.delete(provider);
			}
		}

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
