package mds.tp.api.hackr.tpApi.controllers;

import mds.tp.api.hackr.tpApi.model.Person;
import mds.tp.api.hackr.tpApi.services.FakeIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fakeIdentity")
public class FakeIdentityController {

    private final FakeIdentityService fakeIdentityService;

    @Autowired
    public FakeIdentityController(FakeIdentityService fakeIdentityService) {
        this.fakeIdentityService = fakeIdentityService;
    }

    @GetMapping
    public ResponseEntity<Person> getFakeIdentity(@RequestParam("language") String language) {
        return ResponseEntity.ok(fakeIdentityService.generateFakePerson(language));
    }
}
