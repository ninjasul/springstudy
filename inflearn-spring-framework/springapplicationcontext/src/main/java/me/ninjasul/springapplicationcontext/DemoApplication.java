package me.ninjasul.springapplicationcontext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

//@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        //ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        BookService bookService = (BookService)context.getBean("bookService");
        System.out.println(bookService.bookRepository != null);
    }
}
