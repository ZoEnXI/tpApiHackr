package mds.tp.api.hackr.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Service
public class PwdTestingService {

    final private static String PWD_LIST_URL = "https://raw.githubusercontent.com/danielmiessler/SecLists/refs/heads/master/Passwords/Common-Credentials/10k-most-common.txt";

    private Set<String> commonPasswords;

    @PostConstruct
    private void init() {
        this.commonPasswords = loadPasswordList();
    }

    public String pwdCheckMsgToDisplay(final String pwd) {
        if (isPwdCommon(pwd)) {
            return "Password is common";
        } else {
            return "Password is not common";
        }
    }

    private boolean isPwdCommon(final String pwd) {
        return commonPasswords.contains(pwd);
    }

    private Set<String> loadPasswordList() {
        try {
            URL url = new URL(PWD_LIST_URL);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                Set<String> passwords = new HashSet<>();
                reader.lines().forEach(passwords::add);
                return passwords;
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load password list", ex);
        }
    }
}
