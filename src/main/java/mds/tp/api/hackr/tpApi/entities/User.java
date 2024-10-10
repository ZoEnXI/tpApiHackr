package mds.tp.api.hackr.tpApi.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import mds.tp.api.hackr.tpApi.enumeration.RoleName;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @UuidGenerator
    String id;

    String username;

    String password;

    @Enumerated(EnumType.STRING)
    RoleName role;

    /**
     * Constructor used for registration
     * @param username String
     * @param password String
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = RoleName.USER;
    }
}
