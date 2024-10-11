package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import mds.tp.api.hackr.tpApi.model.CustomUserDetails;
import mds.tp.api.hackr.tpApi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login(@RequestParam(name = "username") String username, @RequestParam (name = "password") String password, HttpSession httpSession) {

        if(this.usersService.isUserValid(username, password)){
            CustomUserDetails customUserDetails = new CustomUserDetails(this.usersService.findByUsername(username));
            httpSession.setAttribute("user", customUserDetails);
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

}
