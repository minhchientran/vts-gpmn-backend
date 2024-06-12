package viettel.gpmn.platform.cms;

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
                "viettel.gpmn.platform.cms.configs",
                "viettel.gpmn.platform.cms.controllers",
                "viettel.gpmn.platform.cms.repositories",
                "viettel.gpmn.platform.cms.services",
        }
)
public class CmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsServiceApplication.class, args);
    }

}


