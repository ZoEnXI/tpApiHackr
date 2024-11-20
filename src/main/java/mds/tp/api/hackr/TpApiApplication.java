package mds.tp.api.hackr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TpApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpApiApplication.class, args);
    }

}
