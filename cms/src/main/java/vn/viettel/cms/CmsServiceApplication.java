package vn.viettel.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication(
        scanBasePackages = {
                "vn.viettel.core.configs",
                "vn.viettel.core.controllers",
                "vn.viettel.core.data",
                "vn.viettel.core.services",
                "vn.viettel.cms.configs",
                "vn.viettel.cms.controllers",
                "vn.viettel.cms.repositories",
                "vn.viettel.cms.services",
        }
)
public class CmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsServiceApplication.class, args);
    }

}


