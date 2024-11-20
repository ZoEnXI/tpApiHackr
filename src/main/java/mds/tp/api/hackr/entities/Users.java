package mds.tp.api.hackr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import mds.tp.api.hackr.enumeration.RoleName;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {

    @Id
    @UuidGenerator
    String id;

    String username;

    String password;

    @Enumerated(EnumType.STRING)
    RoleName role;

    /**
     * Constructor used for registration
     *
     * @param username String
     * @param password String
     */
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = RoleName.USER;
    }
}
