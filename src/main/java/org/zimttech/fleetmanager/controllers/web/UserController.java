package org.zimttech.fleetmanager.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.zimttech.fleetmanager.dto.UserDto;

@Controller
public class UserController {

    @GetMapping("/login")
    public String showLoginForm(WebRequest request, Model model) {
        return "users/login";
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "users/register";
    }

    @PostMapping("register-user")
    public String postRegistrationForm(@ModelAttribute UserDto userDto) {
        return "users/login";
    }
}
