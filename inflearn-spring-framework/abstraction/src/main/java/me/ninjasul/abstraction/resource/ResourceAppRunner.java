package me.ninjasul.abstraction.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

//@Component
public class ResourceAppRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext resourceLoader;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        /**
         * ApplicationContext 의 타입에 따라 로딩할 리소스의 타입도 결정됨.
         * FileSystemXmlApplicationContext -> FileSystemResource 를 로딩
         * ClassPathXmlApplicationContext -> ClassPathResource 를 로딩
         * WebApplicationContext -> ServletContextResource 를 로딩
         *
         * 그러나, ApplicationContext 의 타입에 상관없이 리소스 타입을 강제하려면
         * java.net.URL 접두어 ( classpath:, fil:/// 포함) 중 하나를 사용할 수 있음.
         */
        // AnnotationConfigServletWebServerApplicationContext 타입으로 로딩함.
        System.out.println(resourceLoader.getClass().getSimpleName());

        // classpath: 접두어가 있으면 ClassPathResource 타입으로 로딩함.
        // 해당 접두어가 없는 경우 ServletContextResource 타입으로 로딩함.
        Resource resource = resourceLoader.getResource("classpath:test.txt");
        System.out.println(resource.getClass().getSimpleName());
        System.out.println(resource.exists());

        // ServletContextResource 의 경우 context path 기준으로 로딩을 하는데,
        // 별도로 지정이 되어 있지 않으므로 해당 리소스를 읽는데 실패함.
        resource = resourceLoader.getResource("test.txt");
        System.out.println(resource.getClass().getSimpleName());
        System.out.println(resource.exists());
    }
}