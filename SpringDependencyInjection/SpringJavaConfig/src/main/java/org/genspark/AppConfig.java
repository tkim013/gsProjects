package org.genspark;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean("student1")
    public Student getStudent1(Integer id1, String name1, List<Phone> ph1, Address add1, Quirk quirk1) {
        return new Student(id1, name1, ph1, add1, quirk1);
    }

    @Bean("quirk1")
    public Quirk quirk1() {
        return new Quirk1();
    }

    @Bean("id1")
    public Integer id1() {
        return 42;
    }

    @Bean("name1")
    public String name1() {
        return "Some Name";
    }

    @Bean("ph1")
    public List<Phone> ph1() {
        List<Phone> number = new ArrayList<>();
        number.add(new Phone("1234567890"));
        number.add(new Phone("0987654321"));
        number.add(new Phone("5555555555"));
        return number;
    }

    @Bean("add1")
    public Address add1(String city1, String state1, String country1, String zipCode1) {
        return new Address(city1, state1, country1, zipCode1);
    }

    @Bean("city1")
    public String city1() {
        return "San Diego";
    }

    @Bean("state1")
    public String state1() {
        return "California";
    }

    @Bean("country1")
    public String country1() {
        return "USA";
    }

    @Bean("zipCode1")
    public String zipCode1() {
        return "91911";
    }

    @Bean("student2")
    public Student getStudent2(Integer id2, String name2, List<Phone> ph2, Address add2, Quirk quirk2) {
        return new Student(id2, name2, ph2, add2, quirk2);
    }

    @Bean("quirk2")
    public Quirk quirk2() {
        return new Quirk2();
    }

    @Bean("id2")
    public Integer id2() {
        return 13;
    }

    @Bean("name2")
    public String name2() {
        return "Another Name";
    }

    @Bean("ph2")
    public List<Phone> ph2() {
        List<Phone> number = new ArrayList<>();
        number.add(new Phone("2468024680"));
        number.add(new Phone("0864208642"));
        number.add(new Phone("1111111111"));
        return number;
    }

    @Bean("add2")
    public Address add2(String city2, String state2, String country2, String zipCode2) {
        return new Address(city2, state2, country2, zipCode2);
    }

    @Bean("city2")
    public String city2() {
        return "Phoenix";
    }

    @Bean("state2")
    public String state2() {
        return "Arizona";
    }

    @Bean("country2")
    public String country2() {
        return "USA";
    }

    @Bean("zipCode2")
    public String zipCode2() {
        return "85001";
    }

    @Bean("student3")
    public Student getStudent3(Integer id3, String name3, List<Phone> ph3, Address add3, Quirk quirk3) {
        return new Student(id3, name3, ph3, add3, quirk3);
    }

    @Bean("quirk3")
    public Quirk quirk3() {
        return new Quirk3();
    }

    @Bean("id3")
    public Integer id3() {
        return 100;
    }

    @Bean("name3")
    public String name3() {
        return "Yet Another Name";
    }

    @Bean("ph3")
    public List<Phone> ph3() {
        List<Phone> number = new ArrayList<>();
        number.add(new Phone("1357913579"));
        number.add(new Phone("9753197531"));
        number.add(new Phone("9999999999"));
        return number;
    }

    @Bean("add3")
    public Address add3(String city3, String state3, String country3, String zipCode3) {
        return new Address(city3, state3, country3, zipCode3);
    }

    @Bean("city3")
    public String city3() {
        return "Portland";
    }

    @Bean("state3")
    public String state3() {
        return "Oregon";
    }

    @Bean("country3")
    public String country3() {
        return "USA";
    }

    @Bean("zipCode3")
    public String zipCode3() {
        return "97205";
    }
}
