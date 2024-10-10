package mds.tp.api.hackr.tpApi.services;

import mds.tp.api.hackr.tpApi.entities.User;
import mds.tp.api.hackr.tpApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(final String username) {
       return this.userRepository.findByUsername(username).orElseThrow();
    }

    public void saveNewUser(final String username, final String password) {
        this.userRepository.save(new User(username, this.passwordEncoder.encode(password)));
    }

    public boolean isUserValid(final String username, final String password){
        User user = this.findByUsername(username);
        return this.isPwdCorrect(password, user);
    }

    private boolean isPwdCorrect(final String password, final User user){

        return this.passwordEncoder.matches(password, user.getPassword());
    }
}
