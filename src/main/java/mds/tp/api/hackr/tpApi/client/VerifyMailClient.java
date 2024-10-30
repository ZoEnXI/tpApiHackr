package mds.tp.api.hackr.tpApi.client;

import feign.Response;
import mds.tp.api.hackr.tpApi.configuration.VerifyMailClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "verify-mail", url = "${hunter.url}", configuration = VerifyMailClientConfig.class)
public interface VerifyMailClient {

    @GetMapping("/email-verifier")
    Response verifyMail(@RequestParam("email") String email);
}
