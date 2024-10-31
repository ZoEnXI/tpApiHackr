package mds.tp.api.hackr.tpApi.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class LogAdminService {

    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        this.objectMapper = new ObjectMapper();
    }

    private List<JsonNode> readLogs(Predicate<JsonNode> filter) {
        List<JsonNode> logs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("logs/user_activity.log.json"))) {
            String line;
            while ((line = br.readLine()) != null) {
                JsonNode jsonNode = objectMapper.readTree(line);
                if (filter.test(jsonNode)) {
                    logs.add(jsonNode);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }
        return logs;
    }

    public List<JsonNode> getAllLogs() {
        return readLogs(jsonNode -> true);
    }

    public List<JsonNode> getLogsByFunctionnality(final String functionality) {
        return readLogs(jsonNode ->
                jsonNode.has("functionality") && StringUtils.equals(jsonNode.get("functionality").asText(), functionality)
        );
    }

    public List<JsonNode> getLogsByUsername(final String username) {
        return readLogs(jsonNode ->
                jsonNode.has("userLogged") && StringUtils.equals(jsonNode.get("userLogged").asText(), username)
        );
    }
}
