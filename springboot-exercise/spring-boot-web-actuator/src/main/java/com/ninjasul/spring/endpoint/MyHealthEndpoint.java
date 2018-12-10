package com.ninjasul.spring.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthEndpoint implements HealthIndicator {

    @Autowired
    MyEndpoint  myEndpoint;

    @Override
    public Health health() {
        if( myEndpoint.getValue().equals("down") ) {
            return Health.down()
                    .withDetail("reason", "I don't know why the broadcasting is blocked suddenly.")
                    .build();
        }

        return Health.up().build();
    }
}