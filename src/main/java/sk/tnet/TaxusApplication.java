package sk.tnet;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TaxusApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(TaxusApplication.class).headless(false)
                .web(false).run(args);
        CamelSpringBootApplicationController applicationController = applicationContext
                .getBean(CamelSpringBootApplicationController.class);
        applicationController.run();
    }

}