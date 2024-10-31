package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.tpApi.services.PwdTestingService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
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
    public ResponseEntity<String> generatePwd(HttpSession httpSession) {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "generatePwd");
        log.info("Password generation request received");
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }

    @PostMapping("/checkPwd")
    public ResponseEntity<String> checkPwd(@RequestParam("pwd") String pwd, HttpSession httpSession) {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "checkPwd");
        log.info("Password check request received for password {}", pwd);
        return ResponseEntity.ok(this.pwdTestingService.pwdCheckMsgToDisplay(pwd));
    }
}
