/*
 * bdd-spring-selenium
 * LoginController
 * @since 04/05/2023
 */
package com.example.bddspringselenium.controller;

import com.example.bddspringselenium.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Controller
public class LoginController {

    @GetMapping("/")
    public String root(Model model) {
        return viewLoginPage(model);
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String greetingSubmit(@ModelAttribute User user, Model model) {
        model.addAttribute("user", user);
        return "home";
    }
}
