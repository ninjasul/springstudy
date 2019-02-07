package com.ninjasul.springframework.environment;

import lombok.extern.log4j.Log4j2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.ninjasul.springframework.environment.EnvironmentApplication.STAGING;
import static com.ninjasul.springframework.environment.EnvironmentApplication.TEST;
import static com.ninjasul.springframework.environment.EnvironmentApplicationTests.PROFILE;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = PROFILE)
@Log4j2
public class EnvironmentApplicationTests {

    public static final String PROFILE = TEST;

    @Autowired
    ApplicationContext context;

    Environment environment;

    @Autowired
    BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
        environment = context.getEnvironment();
    }

    @Test
    public void contextLoads() {
        assertNotNull( bookRepository );
        assertTrue( Arrays.toString(environment.getActiveProfiles()).contains(PROFILE));

        log.info("bookRepository: {}", bookRepository.getClass());
        log.info("default profiles: {}", Arrays.toString(environment.getDefaultProfiles()));
        log.info("active profiles: {}", Arrays.toString(environment.getActiveProfiles()));
    }
}

