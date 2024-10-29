package mds.tp.api.hackr.tpApi.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Person {

    @JsonSerialize
    String firstName;

    @JsonSerialize
    String lastName;

    @JsonSerialize
    String nationality;

    @JsonSerialize
    String address;

}
