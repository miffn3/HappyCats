package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import university.happyCatsSpring.dto.RegistrationDto;
import university.happyCatsSpring.dto.UpdateUserDto;
import university.happyCatsSpring.entity.User;
import university.happyCatsSpring.service.iface.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/me")
    public ResponseEntity<User> currentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        Optional<User> userOptional = userService.getUserByUsername(username);

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUserDto body) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        User user = userService.updateUser(username, body);

        return ResponseEntity.ok(user);
    }
}
