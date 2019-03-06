package ninjasul.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "ninjasul.springmvc", excludeFilters = @ComponentScan.Filter(Controller.class))
public class AppConfig {
}