package com.example.thymeleaf;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    UserService userService;
    @GetMapping("/listUsers")
    public String listUsers(Model model) {
        //User user = new User(11,"Adam", "Skaźnik", 43);
        //List<User> users = new ArrayList<>();
        //users.add(new User(1,"Krzysztof","Kowalski",50));
        //users.add(new User(2,"Krzysztof","Jakiś",52));
        //users.add(new User(9,"Grzegorz","Limburski",50));
        //users.add(new User(11,"Adam", "Skaźnik", 43));
        model.addAttribute("users", userService.listUsers());
        //model.addAttribute("styl", "color: red;");
        return "list-users-wiev";
    }

}
