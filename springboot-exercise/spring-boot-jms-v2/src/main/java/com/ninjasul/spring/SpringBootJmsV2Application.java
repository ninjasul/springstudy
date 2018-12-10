package com.ninjasul.spring;

import com.ninjasul.spring.domain.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/**********************************************************************************************************************
 @EnableJms: @JmsListener 어노테이션을 발견하면 message listener container 를 생성함.
 **********************************************************************************************************************/
@SpringBootApplication
@EnableJms
public class SpringBootJmsV2Application {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootJmsV2Application.class);

	// ConnectionFactory 빈은 Spring Boot 에 의해 자동 생성됨.
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
													DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		// This provides all boot's default to this factory, including the message converter
		configurer.configure(factory, connectionFactory);

		// You could still override some of Boot's default if necessary.
		return factory;
	}

	// Serialize message content to json using TextMessage
	// 기본 타입(String, Map, Serializable) 만 자동 변환되므로 Email 객체는 converter를 별도로 정의 해 주어야 함.
	// 스프링 부트는 MessageConverter를 만나면 DefaultJmsListenerContainerFactoryConfigurer 에 의해 생성된
	// 그 MessageConverter를 JmsTemplate과 JmsListenerContainerFactory와 엮어줌.
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	@JmsListener(destination="anothermailbox")
	public void anotherConsumer(String message) {
		logger.info("Another Simpler Consumer: " + message);
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootJmsV2Application.class, args);

		// JmsTemplate 빈은 Spring Boot 에 의해 자동 생성됨.
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		logger.info("Sending an email message.");
		jmsTemplate.convertAndSend("mailbox", new Email("ninjasul@gmail.com", "Hello"));
	}
}
