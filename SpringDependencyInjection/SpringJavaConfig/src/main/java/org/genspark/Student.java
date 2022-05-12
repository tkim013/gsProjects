package org.genspark;

import java.util.List;

public class Student implements Scholar
{
    private Integer id;
    private String name;
    private List<Phone> ph;
    private Address add;
    private Quirk quirk;

    public Student(Integer id, String name, List<Phone> ph, Address add, Quirk q) {
        this.id = id;
        this.name = name;
        this.ph = ph;
        this.add = add;
        this.quirk = q;
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

    @Override
    public void studyHard() {
        String string = quirk.getString();
        System.out.println(string);
    }
}
