package org.genspark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Student
{
    private Integer id;
    private String name;
    private List<Phone> ph;
    private Address add;

    @Autowired
    public void setId(Integer id) {
        this.id = id;
    }

    @Autowired
    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    public void setPh(List<Phone> ph) {
        this.ph = ph;
    }

    @Autowired
    public void setAdd(Address add) {
        this.add = add;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ph=" + ph +
                ", add=" + add +
                '}';
    }
}
