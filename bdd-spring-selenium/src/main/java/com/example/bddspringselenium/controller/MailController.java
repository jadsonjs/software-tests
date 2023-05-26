/*
 *
 * bdd-spring-selenium
 * FormController
 * @since 26/05/2023
 */
package com.example.bddspringselenium.controller;

import com.example.bddspringselenium.model.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@Controller
@Slf4j
public class MailController {


    /**
     * Redirect to mail page
     * @return
     */
    @GetMapping("/mail")
    public String form(Model model) {
        model.addAttribute("mail", new Mail());
        return "mail"; // mail.html
    }

    /**
     * Send a new mail
     * @param mail
     * @param model
     * @return
     */
    @PostMapping("/send")
    public String send(@ModelAttribute Mail mail, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("mail", mail);
        log.info(mail.getSubject());
        log.info("--------------------------");
        log.info(mail.getBody());
        log.info("--------------------------");

        redirectAttributes.addFlashAttribute("message", "Email successfully sent to: "+mail.getTo());
        return "redirect:/mail";
    }

}
