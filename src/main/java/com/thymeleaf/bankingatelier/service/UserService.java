package com.thymeleaf.bankingatelier.service;
import com.thymeleaf.bankingatelier.model.UserModel;
import com.thymeleaf.bankingatelier.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Optional;
import lombok.Data;

//service: a class that contains business logic and act as a middleware between
//the controller (handles http requests) and the repository (handles db operations)

@Service //marks the class as a Spring-managed service component.
@Data  //generates getters, setters, equals, hashCode, and toString methods for all fields
@NoArgsConstructor
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //for registering a user
    public void register(UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
    }

    // login a user
    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    // Deposit money into user's account
    public void deposit(Long userId, double amount) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            user.setBalance(user.getBalance() + amount);
            userRepository.save(user);
        }
        else {
            System.out.println("User with ID " + userId + " not found");
        }
    }

    // Withdraw money from user's account
    public boolean withdraw(Long userId, double amount) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                userRepository.save(user);
                return true; // Withdrawal successful
            } else {
                return false; // Insufficient balance
            }
        } else {
            return false; // User not found
        }
    }

}
