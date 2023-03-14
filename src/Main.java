import dao.*;
import model.Group;
import model.Student;
import util.Log;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Log.info("Loading driver...", Main.class.getName(), Class.class.getName());
            Class.forName("org.postgresql.Driver");
            Log.info("Driver loaded", Main.class.getName(), Class.class.getName());
        } catch (ClassNotFoundException e) {
            Log.error("Driver loading failed", Main.class.getName(), Class.class.getName());
            e.printStackTrace();
        }

        ManagerDao managerDao = ManagerDao.INSTANCE;
        CourseFormatDao courseFormatDao = CourseFormatDao.INSTANCE;
        CourseDao courseDao = CourseDao.INSTANCE;
        MentorDao mentorDao = MentorDao.INSTANCE;
        AddressDao addressDao = AddressDao.INSTANCE;
        GroupDao groupDao = GroupDao.INSTANCE;
        StudentDao studentDao = StudentDao.INSTANCE;


//
//        Student student = new Student();
//        student.setName("Kim");
//        student.setSurname("He");
//        student.setEmail("kimm@mail.ru");
//        student.setPhone("057775556");
//        student.setDob(LocalDate.of(1986, Month.MAY, 1));
//        Group group = new Group();
//        group.setId(1);
//        List<Group> groups = new ArrayList<>();
//        groups.add(group);
//        student.setGroups(groups);
//
//        System.out.println(studentDao.findById(1L));
//
//        System.out.println(studentDao.save(student));
//

        Group group = new Group();
        group.setName("Java 20012023");
        group.setRoom("8");
        group.setStartTime(LocalTime.of(8,30,01));
        Student student = new Student();
        student.setId(2);
        List<Student> students = new ArrayList<>();
        students.add(student);
        group.setStudents(students);

        System.out.println(groupDao.save(group));




/*      Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        manager.setName(scanner.nextLine());

        System.out.println("Surname: ");
        manager.setSurname(scanner.nextLine());

        System.out.println("Phone: ");
        manager.setPhone(scanner.nextLine());

        System.out.println("Email: ");
        manager.setEmail(scanner.nextLine());

        System.out.println("Salary: ");
        manager.setSalary(scanner.nextDouble());
*/
//        System.out.println(managerDao.findById(3L));
    }
}