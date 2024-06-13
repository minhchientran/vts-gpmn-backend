package viettel.gpmn.platform.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication(
        scanBasePackages = {
                "viettel.gpmn.platform.core.configs",
                "viettel.gpmn.platform.core.controllers",
                "viettel.gpmn.platform.core.data",
                "viettel.gpmn.platform.core.services",
                "viettel.gpmn.platform.retail.configs",
                "viettel.gpmn.platform.retail.controllers",
                "viettel.gpmn.platform.retail.repositories",
                "viettel.gpmn.platform.retail.services",
        }
)
public class RetailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetailServiceApplication.class, args);
    }

}
