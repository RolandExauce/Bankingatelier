package com.thymeleaf.bankingatelier.service;
import com.thymeleaf.bankingatelier.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Optional;


//implement user details service from spring framework security core
@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserHandlerService implements UserDetailsService {

    @Autowired
    private   UserService userService;

    //override method loadUserByUsername in userDetailsService interface from spring security core
    //login is handled here
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
        Optional<UserModel> userModelOptional =  userService.findByUsername(username);

        //uncomment this for debugging purposes
       //System.out.println("user modal optional: " + userModelOptional);
        userModelOptional.orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return User.withUsername(userModelOptional.get().getUsername()).password(userModelOptional.get().getPassword()).build();
    }
}
