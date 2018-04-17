package app;

import dao.StudentDao;
import model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        StudentDao studentDao =
                (StudentDao)context.getBean("studentDao");

        System.out.println("------Records Creation--------" );
        studentDao.create("Zara", 11);
        studentDao.create("Nuha", 2);
        studentDao.create("Ayan", 15);

        System.out.println("------Listing Multiple Records--------" );
        List<Student> students = studentDao.listStudents();

        for (Student record : students) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.println(", Age : " + record.getAge());
        }

        System.out.println("----Updating Record with ID = 2 -----" );
        studentDao.update(2, 20);

        System.out.println("----Listing Record with ID = 2 -----" );
        Student student = studentDao.getStudent(2);
        System.out.print("ID : " + student.getId() );
        System.out.print(", Name : " + student.getName() );
        System.out.println(", Age : " + student.getAge());
    }
}