package App.Rest;

import core.users.User;
import org.springframework.web.bind.annotation.*;
import service.Overall;

import java.util.LinkedList;

import static service.Overall.AddUser;

@RestController
public class UserController {
    @PostMapping("/user")
    public User user(@RequestParam(
            value = "name",
            defaultValue = "Worlders"
    ) String name) {
        User user = new User(name, "login", "password");

        return AddUser(user);
    }


    @GetMapping("/user")
    public LinkedList GetUsers(@RequestParam(
            value = "id",
            defaultValue = "0"
    ) int id) {
        return Overall.GetUsers();
    }

    @GetMapping("/user/{id}")
    public User GetUser(@PathVariable int id) {
        return Overall.GetUser(id);

    }

    @PutMapping("/user/")
    public String putMethod(/*@PathVariable int id*/) {
        return "this is put request";
        /*TODO:IMPLEMENT put method */
    }

    @DeleteMapping("/user/")
    public String DeleteMethod(/*@PathVariable int id*/) {
        return "this is delete! request";
        /*TODO: implement delete method */
    }
    @PatchMapping("/user")
    public String PatchMethod(/*@PathVariable int id*/) {
        return "this is patch!1 request";
        /*TODO: implement patch method */
    }

}
