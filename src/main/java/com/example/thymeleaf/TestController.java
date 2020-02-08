package com.example.thymeleaf;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "list-users-view";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute User user, Model model) {
        userService.createUser(user.getImie(), user.getNazwisko(), user.getWiek());
        return "redirect:/listUsers";
    }

}
