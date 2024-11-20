package mds.tp.api.hackr.services;

import com.github.javafaker.Faker;
import mds.tp.api.hackr.model.Person;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class FakeIdentityService {

    public Person generateFakePerson(String language) {

        Faker faker = new Faker(new Locale(language));

        return new Person(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.nation().nationality(),
                faker.address().fullAddress()
        );
    }
}
