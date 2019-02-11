package com.springframework.resourceloader;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ResourceLoaderApplicationTests {

    @Autowired
    ResourceLoader resourceLoader;

    @Test
    public void resourceLoader() {
        assertNotNull(resourceLoader);

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

}

