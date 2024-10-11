package mds.tp.api.hackr.tpApi.controllers;

import mds.tp.api.hackr.tpApi.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpamEmailController {

    private final MailService mailService;

    @Autowired
    public SpamEmailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/spamEmail")
    public void spamEmail(@RequestParam("email") String email, @RequestParam("nbEmail") int nbEmail) {

        for (int i = 0; i < nbEmail; i++){
            this.mailService.sendEmail(email);
        }
    }
}
