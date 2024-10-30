package mds.tp.api.hackr.tpApi.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import mds.tp.api.hackr.tpApi.client.VerifyMailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String mail;

    private final JavaMailSender emailSender;

    private final VerifyMailClient verifyMailClient;

    @Autowired
    public EmailService(JavaMailSender emailSender, VerifyMailClient verifyMailClient) {
        this.emailSender = emailSender;
        this.verifyMailClient = verifyMailClient;
    }

    public String msgToReturn(final String email) throws IOException {

        if(StringUtils.equals(verifyEmail(email), "valid")){
            return "Email valid";
        }
        return "Email invalid";
    }


    private String verifyEmail(final String email) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Response response = this.verifyMailClient.verifyMail(email);
        byte[] a = response.body().asInputStream().readAllBytes();
        JsonNode jsonBody =  objectMapper.readTree(a);
        return jsonBody.get("data").get("status").asText();

    }

    public void sendEmail(final String emailUser, final String subject, final String text, final String gifUrl) {
        try {

            MimeMessage message = this.emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(this.mail, "La Chine");
            helper.setTo(emailUser);
            helper.setSubject(subject);
            helper.setText("<html>" +
                    "<body>" +
                    "<h1>ALERTE</h1>" +
                    "<p>" + text + "</p>" +
                    "<img src='" + gifUrl + "'style='width: 100%; max-width: 600px;'/>" +
                    "</body>" +
                    "</html>", true);

            emailSender.send(message);

        } catch (MessagingException | UnsupportedEncodingException ignored) {
            log.info("Error while sending email {}", ignored.getMessage());
        }
    }
}
