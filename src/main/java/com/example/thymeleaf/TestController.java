package com.example.thymeleaf;

import com.example.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/test")
    public String test(Model model) {
        User user = new User(11,"Adam", "Skaźnik", 43);
        model.addAttribute("user", user);
        model.addAttribute("styl", "color: red;");
        return "test-app";
    }

}
