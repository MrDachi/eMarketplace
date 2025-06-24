package com.controller;

import com.entity.User;
import com.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegister(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(User user, RedirectAttributes ra){
        try {
            userService.register(user);
            return "redirect:/login";
        } catch (Exception e){
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String usernameOrEmail,
            @RequestParam String password,
            HttpSession session,
            RedirectAttributes ra) {
        try {
            User u = userService.login(usernameOrEmail, password);
            session.setAttribute("user", u);
            return "redirect:/items";
        }catch (Exception e){
            ra.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}












