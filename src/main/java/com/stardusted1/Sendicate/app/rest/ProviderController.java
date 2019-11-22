package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.repositories.ProviderRepository;
import com.stardusted1.Sendicate.app.core.users.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/provider")
public class ProviderController {

	@Autowired
	ProviderRepository providerRepository;

    @PostMapping
    public String CreateUsers(@RequestParam(
            value = "number",
            defaultValue = "1"
    ) String number) {
        return "Ok";
    }


    @GetMapping()
    public ArrayList<Provider> GetUsers() {
		return (ArrayList<Provider>) providerRepository.findAll();
    }

    @GetMapping("{id}")
    public String GetUser(@PathVariable int id) {
        return "Overall.GetUser(id);";

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
