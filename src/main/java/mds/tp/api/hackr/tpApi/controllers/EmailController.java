package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.tpApi.services.EmailService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/mail")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/spamMail")
    public void spamEmail(@RequestParam("email") String email, @RequestParam("nbEmail") int nbEmail, @RequestParam("subject") String subject,
                          @RequestParam("text") String text, @RequestParam("gifUrl") String gifUrl, HttpSession httpSession) {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "spamEmail");
        for (int i = 0; i < nbEmail; i++) {
            this.emailService.sendEmail(email, subject, text, gifUrl);
        }
        log.info("Spam mail sent to {}, {} times", email, nbEmail);
    }

    @GetMapping("/verifyMail")
    public ResponseEntity<String> verifyEmail(@RequestParam("email") String email, HttpSession httpSession) throws IOException {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "verifyEmail");
        log.info("Email verification request received for email {}", email);
        return ResponseEntity.ok(this.emailService.msgToReturn(email));
    }
}
