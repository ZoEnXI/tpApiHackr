package mds.tp.api.hackr.tpApi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PwdGeneratorController {

    @GetMapping("/generatePassword")
    public ResponseEntity<String> generatePwd() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }
}
