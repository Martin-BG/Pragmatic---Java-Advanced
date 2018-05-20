package app;

import config.AppConfig;
import dao.employee.EmployeeDao;
import dao.student.StudentDao;
import model.Employee;
import model.Student;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"config"})
public class App {

    public static void main(String[] args) {
        demoStudent();

        demoEmployee();
    }

    private static void demoEmployee() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeDao employeeDao = context.getBean(EmployeeDao.class);

//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        EmployeeDao employeeDao = (EmployeeDao) context.getBean("employeeDao");

        System.out.println("------Records Creation--------");
        employeeDao.create("Zara", 31, "Sales", 1000d);
        employeeDao.create("Nuha", 22, "Marketing", 2000d);
        employeeDao.create("Ayan", 25, "HR", 1500d);

        System.out.println("------Listing Multiple Records--------");
        List<Employee> employees = employeeDao.getAllEmployees();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("----Updating Salary of Record with ID = 1 -----");
        employeeDao.updateSalary(1, 3000d);

        System.out.println("----Listing Record with ID = 1 -----");
        Employee employee = employeeDao.getEmployee(1);
        System.out.println(employee);

        System.out.println("----Delete Record with ID = 2 -----");
        employeeDao.delete(2);

        System.out.println("------Listing Multiple Records--------");
        employees = employeeDao.getAllEmployees();

        for (Employee empl : employees) {
            System.out.println(empl);
        }
    }

    private static void demoStudent() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentDao studentDao = context.getBean(StudentDao.class);
//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        StudentDao studentDao = (StudentDao) context.getBean("studentDao");

        System.out.println("------Records Creation--------");
        studentDao.create("Zara", 11, "Here", "Bulgarian", "Unknown");
        studentDao.create("Nuha", 2, "There", "Lybian", "Unknown");
        studentDao.create("Ayan", 15, "Here", "Unknown", "Unknown");

        System.out.println("------Listing Multiple Records--------");
        List<Student> students = studentDao.getAllStudents();

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("----Updating Age of Record with ID = 2 -----");
        studentDao.updateAge(2, 20);

        System.out.println("----Listing Record with ID = 2 -----");
        Student student = studentDao.getStudent(2);
        System.out.println(student);

        System.out.println("----Updating Nationality of Record with ID = 2 -----");
        studentDao.updateNationality(2, "New Nationality");

        System.out.println("----Listing Record with ID = 2 -----");
        student = studentDao.getStudent(2);
        System.out.println(student);

        System.out.println("----Delete Record with ID = 2 -----");
        studentDao.delete(2);

        System.out.println("------Listing Multiple Records--------");
        students = studentDao.getAllStudents();

        for (Student std : students) {
            System.out.println(std);
        }
    }
}
