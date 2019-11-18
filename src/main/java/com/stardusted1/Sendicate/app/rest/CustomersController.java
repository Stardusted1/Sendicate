package com.stardusted1.Sendicate.app.rest;

import com.stardusted1.Sendicate.app.core.users.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class CustomersController {

    @PostMapping
    public String CreateUsers(@RequestParam(
            value = "number",
            defaultValue = "1"
    ) String number) {
        Customer user = new Customer("First", "login", "password");
        int n = Integer.getInteger(number);
        if (n > 1) {
            for (int i = 1; i < n; i++) {
                user = new Customer("User" + i, "login", "password");
            }
        }
        return "Created "+number+" users";
    }


    @GetMapping()
    public String GetUsers() {
        return "Get method";
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
