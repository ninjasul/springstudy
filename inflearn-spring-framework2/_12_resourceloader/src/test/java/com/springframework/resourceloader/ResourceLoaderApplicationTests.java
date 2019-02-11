package com.springframework.resourceloader;

import lombok.extern.log4j.Log4j2;
import org.apache.catalina.webresources.FileResource;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.ServletContextResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ResourceLoaderApplicationTests {

    @Autowired
    ApplicationContext resourceLoader;

    @Test
    public void readFile() {


        Resource resource = resourceLoader.getResource("classpath:test.txt");
        assertTrue(resource.exists());
        assertTrue(resource.isFile());
        assertFalse(resource.isOpen());
        assertEquals("hello spring", readLineFromFile(resource));

        Resource resource2 = resourceLoader.getResource("test2.txt");
        assertFalse(resource2.exists());
    }

    private String readLineFromFile(Resource resource) {
        try {
            return new BufferedReader(new InputStreamReader(resource.getInputStream())).readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Test
    public void resourceType() {
        assertThat( resourceLoader, instanceOf(WebApplicationContext.class) );
        log.info("ApplicationContextType: {}", resourceLoader.getClass());

        checkResourceType("classpath:test.txt", ClassPathResource.class);
        checkResourceType("file:test.txt", FileUrlResource.class);
        checkResourceType("test.txt", ServletContextResource.class);
    }

    private void checkResourceType(String location, Class<? extends Resource> type) {
        Resource resource = resourceLoader.getResource(location);
        assertThat( resource, instanceOf(type));
        log.info("ResourceType: {}", resource.getClass());
    }
}

