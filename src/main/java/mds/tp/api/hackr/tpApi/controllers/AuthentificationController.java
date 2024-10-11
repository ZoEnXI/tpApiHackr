package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import mds.tp.api.hackr.tpApi.model.CustomUserDetails;
import mds.tp.api.hackr.tpApi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

            return ResponseEntity.ok("You are logged in");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam  String username, @RequestParam  String password) {
        this.usersService.saveNewUser(username, password);
        return ResponseEntity.ok("User registered");
    }

    private void SessionHandling(final String username, final HttpSession httpSession) {
        CustomUserDetails customUserDetails = new CustomUserDetails(this.usersService.findByUsername(username));
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        httpSession.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
    }

}
