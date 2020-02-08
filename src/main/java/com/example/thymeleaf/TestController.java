package com.example.thymeleaf;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {

    public class NotFoundException extends RuntimeException {

    }
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

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable int id, Model model) {
        User user = userService.getUser(id);
        if(user == null) {
            throw new NotFoundException();
        }
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String createUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        validate(user, bindingResult);
            if(bindingResult.hasErrors()) {
                model.addAttribute("user", user);
            return "add-user";
            }
        userService.createUser(user.getImie(), user.getNazwisko(), user.getWiek());
        return "redirect:/listUsers";
    }

    public void validate(User user, BindingResult bindingResult) {
        if (user.getImie() == null || user.getImie().isEmpty()) {
            bindingResult.addError(new ObjectError("imie", "MUSISZ PODAĆ IMIĘ"));
        }
        if (user.getNazwisko() == null || user.getNazwisko().isEmpty()) {
            bindingResult.addError(new ObjectError("nazwisko", "MUSISZ PODAĆ NAZWISKO"));
        }
        if (user.getWiek() < 18) {
            bindingResult.addError(new ObjectError("wiek", "MUSISZ BYĆ PEŁNOLETNI!!!!"));
        }

    }


}
