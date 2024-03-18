package com.thymeleaf.bankingatelier.controller;
import com.thymeleaf.bankingatelier.model.UserModel;
import com.thymeleaf.bankingatelier.service.UserHandlerService;
import com.thymeleaf.bankingatelier.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//controller to handle user related requests
@Controller
@AllArgsConstructor
@RequestMapping("/home")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserHandlerService userHandlerService;

    @Autowired
    private HomeController homeController;

    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserModel user) {
        try {
            userService.register(user);
            return "redirect:/home/authenticate"; // Redirect to login page if registration is successful
        } catch (Exception e) {
            // Redirect back to registration page with error message if registration fails
            return "redirect:/home/registration?error";
        }
    }

    @GetMapping("/authenticate")
    public String login() {
        return "login";
    }


    //login controller post
    @PostMapping("/authenticate")
    @ResponseBody
    public String loginUser(@ModelAttribute("user") UserModel user) {
        try {
            // Validate login using the UserHandlerService
            userHandlerService.loadUserByUsername(user.getUsername());
            return "redirect:/home";
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }


    // endpoint deposit money
    @PostMapping("/{userId}/deposit")
    public String depositMoney(@PathVariable Long userId, @RequestParam double amount) {
        System.out.println("UserId in deposit: " + userId);
        System.out.println("User amount in deposit: " + amount);
        userService.deposit(userId, amount);
        return "redirect:/home/" + userId;
    }


    // Endpoint to withdraw money from user's account
    @PostMapping("/{userId}/withdraw")
    public String withdrawMoney(@PathVariable Long userId, @RequestParam double amount, Model model) {
//        System.out.println("UserId in withdraw: " + userId);
//        System.out.println("User amount in withdraw: " + amount);

        boolean withdrawalSuccessful = userService.withdraw(userId, amount);
        if (!withdrawalSuccessful) {
            model.addAttribute("withdrawalError", "Insufficient balance");
        }
        return "redirect:/home/" + userId;
    }














}
