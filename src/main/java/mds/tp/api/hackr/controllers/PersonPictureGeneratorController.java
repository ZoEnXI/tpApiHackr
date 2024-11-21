package mds.tp.api.hackr.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.services.PersonPictureGeneratorService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/person-picture-generator")
public class PersonPictureGeneratorController {

    private final PersonPictureGeneratorService personPictureGeneratorService;

    @Autowired
    public PersonPictureGeneratorController(PersonPictureGeneratorService personPictureGeneratorService) {
        this.personPictureGeneratorService = personPictureGeneratorService;
    }

    @GetMapping(value = "/generate-picture", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPersonPicture(HttpSession httpSession) throws IOException {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "generatePicture");
        log.info("Generation of a person picture request received");
        return this.personPictureGeneratorService.generatePicture();
    }
}
