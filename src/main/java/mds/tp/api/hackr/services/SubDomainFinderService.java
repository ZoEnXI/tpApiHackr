package mds.tp.api.hackr.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.client.SubdomainFinderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SubDomainFinderService {

    private final SubdomainFinderClient subdomainFinderClient;

    @Autowired
    public SubDomainFinderService(SubdomainFinderClient subdomainFinderClient) {
        this.subdomainFinderClient = subdomainFinderClient;
    }

    public List<String> getSubdomainFromDomain(final String domain) throws IOException {

        List<String> subdomainsList = new ArrayList<>();
        JsonNode response = getResponse(domain);

        response.get("subdomains").forEach(subdomain -> {
            subdomainsList.add(subdomain.asText());
        });

        return subdomainsList;
    }

    private JsonNode getResponse(final String domain) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Response response = this.subdomainFinderClient.findSubdomains(domain);
        byte[] a = response.body().asInputStream().readAllBytes();
        return objectMapper.readTree(a);
    }

}
