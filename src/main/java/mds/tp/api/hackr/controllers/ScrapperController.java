package mds.tp.api.hackr.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.services.ScrapperService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import serpapi.SerpApiSearchException;

@Slf4j
@RestController
@RequestMapping("/scrapper")
public class ScrapperController {

    private final ScrapperService scrapperService;

    @Autowired
    public ScrapperController(ScrapperService scrapperService) {
        this.scrapperService = scrapperService;
    }

    @GetMapping("/search")
    public ResponseEntity<JsonNode> getInformation(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("country") String country,
            @RequestParam("language") String language,
            HttpSession httpSession
    ) throws SerpApiSearchException, JsonProcessingException {

        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "generatePwd");
        log.info("Scrapper request received for {} {} in {} with language {}", firstName, lastName, country, language);
        return ResponseEntity.ok(this.scrapperService.getInformationAboutAPerson(firstName, lastName, country, language));
    }
}
