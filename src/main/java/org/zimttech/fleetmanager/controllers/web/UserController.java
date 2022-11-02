package org.zimttech.fleetmanager.controllers.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.zimttech.fleetmanager.dto.UserDto;
import org.zimttech.fleetmanager.enums.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/login")
    public String showLoginForm(WebRequest request, Model model) {
        return "main/login";
    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "main/register";
    }
    @PostMapping("register-user")
    public String postRegistrationForm(@ModelAttribute UserDto userDto) {
        return "main/login";
    }
    @GetMapping("/dashboard")
    public String configureUserDashboard(@AuthenticationPrincipal UserDetails userDetails,
                                         Model model, HttpServletRequest request) {
        log.info("Adding attributes to model");
        HttpSession session = request.getSession();
        String authenticated = (String) session.getAttribute("AUTHENTICATED");
        String role = (String) session.getAttribute("role");
        String firstName = (String) session.getAttribute("firstName");
        String surname = (String) session.getAttribute("surname");
        String username = (String) session.getAttribute("username");
        Long userId = (Long) session.getAttribute("userId");

        //Attributes
        model.addAttribute("authenticated", authenticated);
        model.addAttribute("role", role);
        model.addAttribute("firstName", firstName);
        model.addAttribute("surname", surname);
        model.addAttribute("username", username);
        model.addAttribute("userId", userId);

        if(role.equalsIgnoreCase(Role.DRIVER.name())){
            log.info("Redirecting to driver dashboard");
            return "users/driver/dashboard";
        }else if(role.equalsIgnoreCase(Role.SUPERVISOR.name())){
            log.info("Redirecting to supervisor dashboard");
            return "users/supervisor/dashboard";
        }else {
            log.info("This should not happen");
            return "redirect:/logout";
        }

    }

    @GetMapping("/login/error")
    public String showLoginErrorPage(WebRequest request, Model model) {
        model.addAttribute("errors", "Invalid Credentials. Please try again.");
        return "main/login";
    }
}
