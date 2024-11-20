package mds.tp.api.hackr.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpSession;
import mds.tp.api.hackr.services.LogAdminService;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/log")
public class LogAdminController {

    private final LogAdminService logAdminService;

    @Autowired
    public LogAdminController(LogAdminService logAdminService) {
        this.logAdminService = logAdminService;
    }

    @GetMapping("/all-Logs")
    public ResponseEntity<List<JsonNode>> getAllLogs(HttpSession httpSession) throws IOException {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "getAllLogs");
        return ResponseEntity.ok(this.logAdminService.getAllLogs());
    }

    @GetMapping("/functionality/{functionality}")
    public ResponseEntity<List<JsonNode>> getLogsByFunctionnality(@PathVariable("functionality") String functionality,
                                                                  HttpSession httpSession) {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "getLogsByFunctionnality");
        return ResponseEntity.ok(this.logAdminService.getLogsByFunctionnality(functionality));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<JsonNode>> getLogsByUsername(@PathVariable("username") String username,
                                                            HttpSession httpSession) {
        MDC.put("userLogged", httpSession.getAttribute("userLogged").toString());
        MDC.put("functionality", "getLogsByUsername");
        return ResponseEntity.ok(this.logAdminService.getLogsByUsername(username));
    }
}
