package mds.tp.api.hackr.tpApi.services;

import mds.tp.api.hackr.tpApi.entities.Users;
import mds.tp.api.hackr.tpApi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users findByUsername(final String username) {
        return this.usersRepository.findByUsername(username).orElseThrow();
    }

    public void saveNewUser(final String username, final String password) {
        if (this.usersRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        this.usersRepository.save(new Users(username, this.passwordEncoder.encode(password)));
    }

    public boolean isUserValid(final String username, final String password) {
        Users users = this.findByUsername(username);
        return this.isPwdCorrect(password, users);
    }

    private boolean isPwdCorrect(final String password, final Users users) {

        return this.passwordEncoder.matches(password, users.getPassword());
    }
}
