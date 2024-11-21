package mds.tp.api.hackr.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "person-picture-generator", url = "${thisPersonDoesNotExist.url}")
public interface PersonPictureGeneratorClient {

    @GetMapping
    Response getPersonPicture();
}
