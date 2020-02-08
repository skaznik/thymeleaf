package com.example.thymeleaf;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

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

    //@GetMapping("/getUser/{id}")
    //public String getUser(@PathVariable int id, Model model) {
        //User user = userService.getUser(id);
       // if(user == null) {
            //throw new NotFoundException();
       // }
       // model.addAttribute("user", user);
       // return "user-details";
    //}

    @GetMapping("/getUser/{id}")
    public String getUser(@PathVariable String id, Model model) {
        int i = Integer.parseInt(id);
        User user = userService.getUser(i);
        if(user == null) {
            throw new NumberFormatException();
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
@ExceptionHandler(NotFoundException.class)
public String notFound() {
        return "404";
}

@ExceptionHandler(NumberFormatException.class)
public String notNumber() {
        return "103";
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
