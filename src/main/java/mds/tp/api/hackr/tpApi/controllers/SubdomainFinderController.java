package mds.tp.api.hackr.tpApi.controllers;

import mds.tp.api.hackr.tpApi.services.SubDomainFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/subdomainFinder")
public class SubdomainFinderController {

    private final SubDomainFinderService subDomainFinderService;

    @Autowired
    public SubdomainFinderController(SubDomainFinderService subDomainFinderService) {
        this.subDomainFinderService = subDomainFinderService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<String>> findSubdomains(@RequestParam("domain") String domain) throws IOException {
        return ResponseEntity.ok(this.subDomainFinderService.getSubdomainFromDomain(domain));
    }
}
