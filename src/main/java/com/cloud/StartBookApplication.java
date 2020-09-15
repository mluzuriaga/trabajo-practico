package com.cloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class StartBookApplication extends SpringBootServletInitializer {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartBookApplication.class, args);
    }

    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("The Practice of network security monitoring", "Richard Bejtlich", new BigDecimal("15.41")));
            repository.save(new Book("Los hombres que susurraban a las máquinas", "Antonio Salas", new BigDecimal("9.69")));
            repository.save(new Book("Ciberseguridad: consejos para tener vidas digitales más seguras", "Mónica Valle", new BigDecimal("47.99")));
        };
    }
}