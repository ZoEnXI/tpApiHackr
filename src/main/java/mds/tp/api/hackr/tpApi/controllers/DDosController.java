package mds.tp.api.hackr.tpApi.controllers;

import mds.tp.api.hackr.tpApi.services.DDosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ddos")
public class DDosController {

    private final DDosService ddosService;

    public DDosController(DDosService ddosService) {
        this.ddosService = ddosService;
    }

    @GetMapping
    public ResponseEntity<String> ddos(@RequestParam("url") String url, @RequestParam("nbRequest") Integer nbRequest) {
        this.ddosService.ddos(url, nbRequest);
        return ResponseEntity.ok("DDOS executed");
    }
}
