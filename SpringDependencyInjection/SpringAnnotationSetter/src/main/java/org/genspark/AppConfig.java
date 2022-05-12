package org.genspark;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "org.genspark")
public class AppConfig {

    @Bean
    Integer getId() {
        return 50;
    }

    @Bean
    @Qualifier("name")
    String getName() {
        return "Some Name";
    }

    @Bean
    List<Phone> getPh() {
        List<Phone> number = new ArrayList<>();
        number.add(new Phone());
        number.get(0).setMob("1234567890");
        number.add(new Phone());
        number.get(1).setMob("0987654321");
        number.add(new Phone());
        number.get(2).setMob("5555555555");
        return number;
    }

    @Bean
    Address getAdd() {
        return new Address();
    }

    @Bean(name = "getCity")
    String getCity() {
        return "Columbus";
    }

    @Bean(name = "getState")
    String getState() {
        return "Ohio";
    }

    @Bean(name = "getCountry")
    String getCountry() {
        return "USA";
    }

    @Bean(name = "getZipCode")
    String getZipCode() {
        return "43081";
    }
}
