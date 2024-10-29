package mds.tp.api.hackr.tpApi.controllers;

import mds.tp.api.hackr.tpApi.services.PwdTestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/pwd")
public class PwdController {

    private final PwdTestingService pwdTestingService;

    @Autowired
    public PwdController(PwdTestingService pwdTestingService) {
        this.pwdTestingService = pwdTestingService;
    }

    private String msgToDisplay;

    @GetMapping("/generate")
    public ResponseEntity<String> generatePwd() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<String> checkPwd(@RequestParam("pwd") String pwd) {
        return ResponseEntity.ok(this.pwdTestingService.pwdCheckMsgToDisplay(pwd));
    }
}
