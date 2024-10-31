package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.tpApi.model.Person;
import mds.tp.api.hackr.tpApi.services.FakeIdentityService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/fakeIdentity")
public class FakeIdentityController {

    private final FakeIdentityService fakeIdentityService;

    @Autowired
    public FakeIdentityController(FakeIdentityService fakeIdentityService) {
        this.fakeIdentityService = fakeIdentityService;
    }

    @GetMapping
    public ResponseEntity<Person> getFakeIdentity(@RequestParam("language") String language, HttpSession httpSession) {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "fakeIdentity");
        log.info("Fake identity request received for language {}", language);
        return ResponseEntity.ok(fakeIdentityService.generateFakePerson(language));
    }
}
