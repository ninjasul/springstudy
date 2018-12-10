package me.ninjasul.databinding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppRunner implements ApplicationRunner {

    @Autowired
    ConversionService conversionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(String.valueOf(conversionService));
        log.info(conversionService.getClass().getSimpleName());
    }
}