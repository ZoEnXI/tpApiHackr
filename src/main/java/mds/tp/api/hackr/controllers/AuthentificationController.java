package mds.tp.api.hackr.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.model.CustomUserDetails;
import mds.tp.api.hackr.services.UsersService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthentificationController {

    private final UsersService usersService;

    @Autowired
    public AuthentificationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpSession httpSession) {

        if (this.usersService.isUserValid(username, password)) {
            SessionHandling(username, httpSession);
            httpSession.setAttribute("userLogged", username);
            MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
            log.info("User {} logged in", username);
            return ResponseEntity.ok("You are logged in");

        } else {
            log.info("Invalid credentials, failed connexion attempt for User {}", username);
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username, @RequestParam String password) {

        try {
            this.usersService.saveNewUser(username, password);
            return ResponseEntity.ok("User registered");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
    }

    private void SessionHandling(final String username, final HttpSession httpSession) {
        CustomUserDetails customUserDetails = new CustomUserDetails(this.usersService.findByUsername(username));
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

}
