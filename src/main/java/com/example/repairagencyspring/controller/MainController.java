package com.example.repairagencyspring.controller;

import com.example.repairagencyspring.model.User;
import com.example.repairagencyspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@ComponentScan(basePackages = "com.example.repagentspring.demo.service")
public class MainController {

    private Logger logger = Logger.getLogger(MainController.class);


    @Autowired
    UserService userService;

    @GetMapping("/successLogin")
    public String loginPageRedirect(Model model, Authentication authResult) throws IOException {

        String role = authResult.getAuthorities().toString();

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(principal.getId());

        if (role.contains("ROLE_MANAGER")) {
            return "redirect:/manager";
        } else if (role.contains("ROLE_MASTER")) {
            return "redirect:/master";
        } else {
            return "redirect:/user";
        }

    }

    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/signIn")
    public String loginFail(@RequestParam(name = "error") boolean error, Model model) {
        if (error) {
            model.addAttribute("wrongData", true);
        }
        return "signIn";
    }


    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Authentication authentication) {
        return "error";
    }

    @GetMapping ("/signUp")
    public String registrationWithLog(Model model) {
        model.addAttribute("userForm",new User());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "signUp";
        }

        if (!userService.saveUser(userForm)){
            model.addAttribute("alreadyExist", true);
            return "signUp";
        }
        return "redirect:/";
    }

}

