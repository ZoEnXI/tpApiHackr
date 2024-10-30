package mds.tp.api.hackr.tpApi.controllers;

import mds.tp.api.hackr.tpApi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
                          @RequestParam("text") String text, @RequestParam("gifUrl") String gifUrl) {

        for (int i = 0; i < nbEmail; i++){
            this.emailService.sendEmail(email, subject, text, gifUrl);
        }
    }

    @GetMapping("/verifyMail")
    public ResponseEntity<String> verifyEmail(@RequestParam("email") String email) throws IOException {
        return ResponseEntity.ok(this.emailService.msgToReturn(email));
    }
}
