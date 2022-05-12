package org.genspark;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Student student1 = (Student) context.getBean("student1");
        System.out.println(student1);
        student1.studyHard();

        Student student2 = (Student) context.getBean("student2");
        System.out.println(student2);
        student2.studyHard();

        Student student3 = (Student) context.getBean("student3");
        System.out.println(student3);
        student3.studyHard();
    }
}
