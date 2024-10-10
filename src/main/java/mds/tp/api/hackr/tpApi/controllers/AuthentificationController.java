package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import mds.tp.api.hackr.tpApi.model.CustomUserDetails;
import mds.tp.api.hackr.tpApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthentificationController {

    private final UserService userService;

    @Autowired
    public AuthentificationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestAttribute(name = "username") String username, @RequestAttribute(name = "password") String password, HttpSession httpSession) {

        if(this.userService.isUserValid(username, password)){
            CustomUserDetails customUserDetails = new CustomUserDetails(this.userService.findByUsername(username));
            httpSession.setAttribute("user", customUserDetails);
            return ResponseEntity.ok("You are logged in");

        } else {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestAttribute String username, @RequestAttribute String password) {
        this.userService.saveNewUser(username, password);
        return ResponseEntity.ok("User registered");
    }

}
