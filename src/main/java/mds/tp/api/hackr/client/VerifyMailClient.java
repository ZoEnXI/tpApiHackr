package mds.tp.api.hackr.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "verify-mail", url = "${hunter.url}")
public interface VerifyMailClient {

    @GetMapping
    Response verifyMail(@RequestParam("email") String email);
}
