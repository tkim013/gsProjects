package org.genspark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "org.genspark")
public class AppConfig {

    @Bean
    Integer id() {
        return 23;
    }

    @Bean
    String name() {
        return "Some Other Name";
    }

    @Bean
    List<Phone> ph() {
        List<Phone> number = new ArrayList<>();
        number.add(new Phone("1234567890"));
        number.add(new Phone("0000000000"));
        number.add(new Phone("0987654321"));
        return number;
    }

    @Bean
    Address address() {
        return new Address("Atlanta", "Georgia", "USA", "30311");
    }
}
