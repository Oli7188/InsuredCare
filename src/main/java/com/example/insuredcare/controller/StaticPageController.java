package com.example.insuredcare.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {

    @GetMapping("/Domov")
    public String showHome() {
        return "forward:/index.html";  // Statická stránka, bez prípony .html v URL
    }

    @GetMapping("/Onás")
    public String showAbout() {
        return "forward:/Onás.html";
    }

    @GetMapping("/Prihlasitsa")
    public String showLogin() {
        return "forward:/Prihlasitsa.html";
    }

    @GetMapping("/Registracia")
    public String showRegister() {
        return "forward:/registracia.html";
    }
}
