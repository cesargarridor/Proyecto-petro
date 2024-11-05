package gastos.core_microservice_gastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CoreMicroserviceGastosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreMicroserviceGastosApplication.class, args);
    }

}
