package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.tpApi.services.SubDomainFinderService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subdomainFinder")
public class SubdomainFinderController {

    private final SubDomainFinderService subDomainFinderService;

    @Autowired
    public SubdomainFinderController(SubDomainFinderService subDomainFinderService) {
        this.subDomainFinderService = subDomainFinderService;
    }

    @GetMapping("/find")
    public ResponseEntity<List<String>> findSubdomains(@RequestParam("domain") String domain, HttpSession httpSession) throws IOException {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "findSubdomains");
        log.info("Subdomain finder request received for domain {}", domain);
        return ResponseEntity.ok(this.subDomainFinderService.getSubdomainFromDomain(domain));
    }
}
