package org.genspark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "org.genspark")
public class AppConfig {

    @Bean
    Integer getId() {
        Integer i = 50;
        return i;
    }

    @Bean
    String getName() {
        return "Some Name";
    }

    @Bean
    List<Phone> getPh() {
        List<Phone> number = new ArrayList<>();
        number.add(new Phone("1234567890"));
        number.add(new Phone("0000000000"));
        number.add(new Phone("0987654321"));
        return number;
    }

    @Bean
    Address getAdd() {
        Address add = new Address("Atlanta", "Georgia", "USA", "30311");
        return add;
    }
}
