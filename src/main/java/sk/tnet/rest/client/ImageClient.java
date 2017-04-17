package sk.tnet.rest.client;

import java.awt.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ImageClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${taxus.app.image.url-template}")
    private String urlTemplate;

    @Value("${taxus.app.dir.index}")
    private String dirIndex;

    public Image imageForNumber(String number) {
        return restTemplate.getForObject(urlTemplate, Image.class, dirIndex, number);
    }
}
