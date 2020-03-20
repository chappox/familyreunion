package teamhierro.familyreunion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import teamhierro.familyreunion.model.Login;
import teamhierro.familyreunion.service.RegisterService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    RegisterService register;

    @GetMapping("/register")
    public String showForm(Login login) {
        return "register";
    }

    @PostMapping("register")
    public String validateRegistration(@Valid Login login, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        } else {
            System.out.println("There were no errors. Proceeding to check if email has been used.");

            if (!register.checkUserByEmail(login.getEmail())) {
                System.out.println("Email was not found in DB. Proceeding to check if username has been used.");

                if (register.checkUserByUsername(login.getUsername())) {
                    System.out.println("Username was not found in DB. Adding user to database.");
                    return "registerResult";
                }
            }
            return "register";
        }
    }
}