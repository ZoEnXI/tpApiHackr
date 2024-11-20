package mds.tp.api.hackr.client;

import feign.Response;
import mds.tp.api.hackr.configuration.SubdomainFinderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subdomain-finder", url = "${securityTrail.url}", configuration = SubdomainFinderConfiguration.class)
public interface SubdomainFinderClient {

    @GetMapping("/{domain}/subdomains")
    Response findSubdomains(@PathVariable("domain") String domain);
}
