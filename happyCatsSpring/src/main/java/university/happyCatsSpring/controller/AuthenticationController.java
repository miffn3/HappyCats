package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import university.happyCatsSpring.dto.RegistrationDto;
import university.happyCatsSpring.dto.JwtRequest;
import university.happyCatsSpring.security.JwtToken;
import university.happyCatsSpring.service.iface.UserService;
import university.happyCatsSpring.service.impl.JwtUserDetailsService;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService jwtUserDetailsService;
    private JwtToken jwtToken;

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager,
                          JwtUserDetailsService jwtUserDetailsService, JwtToken jwtToken) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtToken = jwtToken;
    }

    @PostMapping("/sigin")
    public ResponseEntity<?> signin(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.ok("Bearer " + token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody RegistrationDto body) throws ValidationException
    {
        String username = body.getUsername();
        if (userService.existsUserByUsername(username)) {
            throw new ValidationException("Username already existed");
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(body.getPassword());
        userService.createUser(username, encodedPassword, body.getName(), body.getEmail());

        UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(username);
        String token = jwtToken.generateToken(userDetails);

        return ResponseEntity.ok("Bearer " + token);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
