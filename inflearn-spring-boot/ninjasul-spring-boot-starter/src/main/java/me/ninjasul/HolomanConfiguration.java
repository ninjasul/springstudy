package me.ninjasul;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HolomanProperties.class)
public class HolomanConfiguration {

    private static final String DEFAULT_NAME = "ninjasul";
    private static final int DEFAULT_DAYS = 5;

    @Bean
    @ConditionalOnMissingBean
    public Holoman holoman(HolomanProperties properties) {
        Holoman holoman = new Holoman();

        if( properties != null ) {

            if( properties.getName() != null ) {
                holoman.setName(properties.getName());
            }
            else {
                holoman.setName(DEFAULT_NAME);
            }


            if( properties.getHowLong() > 0 ) {
                holoman.setHowLong(properties.getHowLong());
            }
            else {
                holoman.setHowLong(DEFAULT_DAYS);
            }
        }
        else {
            holoman.setName(DEFAULT_NAME);
            holoman.setHowLong(DEFAULT_DAYS);
        }

        return holoman;
    }
}