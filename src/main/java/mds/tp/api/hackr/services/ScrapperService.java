package mds.tp.api.hackr.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import serpapi.GoogleSearch;
import serpapi.SerpApiSearchException;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScrapperService {

     private final ObjectMapper objectMapper;

     @Value("${serpApi.apiKey}")
     private String apiKey;

     @Autowired
        public ScrapperService(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

     public JsonNode getInformationAboutAPerson(final String firstName, final String lastName, final String country, final String language) throws SerpApiSearchException, JsonProcessingException {

          GoogleSearch search = new GoogleSearch(getParameters(firstName, lastName, country, language));
          return this.objectMapper.readTree(search.getJson().toString());
     }

     @NotNull
     private Map<String, String> getParameters(final String firstName, final String lastName, final String country, final String language) {
          Map<String, String> params = new HashMap<>();
          params.put("api_key", this.apiKey);
          params.put("q", firstName + " " + lastName);
          params.put("gl", country);
          params.put("hl", language);
          return params;
     }
}
