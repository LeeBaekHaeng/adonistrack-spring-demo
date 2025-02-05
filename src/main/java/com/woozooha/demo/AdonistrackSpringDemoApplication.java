package com.woozooha.demo;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class AdonistrackSpringDemoApplication {

	static MariaDB4jSpringService mariaDB4jSpringService = new MariaDB4jSpringService();

	public static void main(String[] args) {
		initDb();
		SpringApplication.run(AdonistrackSpringDemoApplication.class, args)
		.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(ContextClosedEvent e) {
				mariaDB4jSpringService.stop();
			}
		});

		log.info("http://localhost:8080/greeting/1");
		log.info("http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/");
		log.info("http://localhost:8080/webjars/adonistrack-ui/html/invocations.html");
	}

	@SneakyThrows
	private static void initDb() {
		mariaDB4jSpringService.setDefaultPort(3309);
		mariaDB4jSpringService.start();

		mariaDB4jSpringService.getDB().createDB("demo");
		mariaDB4jSpringService.getDB().source("schema.sql");
		mariaDB4jSpringService.getDB().source("data.sql");
	}

}
