package com.thymeleaf.bankingatelier.controller;
import com.thymeleaf.bankingatelier.model.UserModel;
import com.thymeleaf.bankingatelier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/home")
public class MyAuthController {

    @Autowired
    private UserService userService;

    // get currently logged auth
    @GetMapping("/auth")
    public ResponseEntity<?> authenticateUser() {

        // Get the authenticated user's information
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get the username
        Optional<UserModel> userModelOptional = userService.findByUsername(username);

        //check if object contains a value
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("username", username);
            responseData.put("userBalance", userModel.getBalance());
            responseData.put("userId",  userModelOptional.get().getId());
            return ResponseEntity.ok(responseData);

        } else {
            // no user found
            return ResponseEntity.notFound().build();
        }
    }


    //when balance updated
    @GetMapping("/home/{userId}")
    public ResponseEntity<?> getUserUpdatedBalance(@PathVariable Long userId) {

        //actually userId is useless here ?

        // Get the authenticated user's information
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get the username
        Optional<UserModel> userModelOptional = userService.findByUsername(username);

        // Check if the user exists and return their balance
        if (userModelOptional.isPresent()) {
            UserModel userModel = userModelOptional.get();
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("newUserBalance", userModel.getBalance());
            return ResponseEntity.ok(responseData);
        } else {
            // No user found
            return ResponseEntity.notFound().build();
        }
    }


}
