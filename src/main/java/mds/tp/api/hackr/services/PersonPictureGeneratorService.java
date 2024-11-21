package mds.tp.api.hackr.services;

import feign.Response;
import mds.tp.api.hackr.client.PersonPictureGeneratorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PersonPictureGeneratorService {

    private final PersonPictureGeneratorClient personPictureGeneratorClient;

    @Autowired
    public PersonPictureGeneratorService(PersonPictureGeneratorClient personPictureGeneratorClient) {
        this.personPictureGeneratorClient = personPictureGeneratorClient;
    }

    public byte[] generatePicture() throws IOException {

        Response response =  this.personPictureGeneratorClient.getPersonPicture();
        return response.body().asInputStream().readAllBytes();
    }
}
