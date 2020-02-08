package com.example.thymeleaf;

import com.example.thymeleaf.model.SkierowanieDoLekarza;
import com.example.thymeleaf.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientControler {

    @Autowired
    PatientService patientService;

    @GetMapping("/lista")
    public String lista(Model model) {
       model.addAttribute("skierowanie", PatientService.listaSkierowan);
       return "lista-skierowan";
    }

    @GetMapping("/add")
    public String createSkierowanie(Model model) {
        model.addAttribute("skierowanie",new SkierowanieDoLekarza());
        return "add";
    }
    @PostMapping("/add")
    public String createSkierowanie(@ModelAttribute SkierowanieDoLekarza skierowanieDoLekarza, Model model) {
        return "redirect: /lista";
    }





}
