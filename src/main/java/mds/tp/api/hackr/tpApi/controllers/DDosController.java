package mds.tp.api.hackr.tpApi.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.tpApi.services.DDosService;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/ddos")
public class DDosController {

    private final DDosService ddosService;

    public DDosController(DDosService ddosService) {
        this.ddosService = ddosService;
    }

    @GetMapping
    public ResponseEntity<String> ddos(@RequestParam("url") String url, @RequestParam("nbRequest") Integer nbRequest,
                                       HttpSession httpSession) {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "DDOS");
        log.info("DDOS request received for url {} with {} requests", url, nbRequest);
        this.ddosService.ddos(url, nbRequest);
        log.info("DDOS finished");
        return ResponseEntity.ok("DDOS executed");
    }
}
