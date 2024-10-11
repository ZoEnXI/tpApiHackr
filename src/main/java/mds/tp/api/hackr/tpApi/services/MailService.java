package mds.tp.api.hackr.tpApi.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String mail;

    private String gifUrl = "https://c.tenor.com/9r1rE1-uyFYAAAAd/tenor.gif";

    private final JavaMailSender emailSender;

    @Autowired
    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String emailUser) {
        try {

            MimeMessage message = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.mail, "La Chine");
            helper.setTo(emailUser);
            helper.setSubject("ALERTE");
            helper.setText("<html>" +
                    "<body>" +
                    "<h1>ALERTE</h1>" +
                    "<img src='" + this.gifUrl + "' alt='Social Credit GIF' style='width: 100%; max-width: 600px;'/>" +
                    "</body>" +
                    "</html>", true);

            emailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException ignored) {
            log.info("Error while sending email {}", ignored.getMessage());
        }
    }
}
