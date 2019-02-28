package ninjasul.springmvc.configuration;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String getName() {
        return "ninjasul";
    }
}