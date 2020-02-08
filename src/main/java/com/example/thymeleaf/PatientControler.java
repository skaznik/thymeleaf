package com.example.thymeleaf;

import com.example.thymeleaf.model.SkierowanieDoLekarza;
import com.example.thymeleaf.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientControler {

    @Autowired
    PatientService patientService;

    @GetMapping("/lista")
    public void lista(Model model) {
       //model.addAttribute("skierowanie", PatientService.listaSkierowania);
    }

    @GetMapping("/add")
    public String createSkierowanie(Model model) {
        model.addAttribute("skierowanie",new SkierowanieDoLekarza());
        return "add";
    }





}
