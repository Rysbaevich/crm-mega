import dao.ManagerDao;
import dao.impl.ManagerDaoImpl;
import model.Manager;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
/*        Course course = new Course();
        course.setId(1);
        course.setName("Java");
        course.setPrice(15000);

        CourseFormat bootcamp = new CourseFormat();
        bootcamp.setId(1);
        bootcamp.setOnline(true);
        bootcamp.setFormat("Bootcamp");
        bootcamp.setLessonCountPerWeek(5);
        bootcamp.setDurationInWeek(18);
        bootcamp.setLessonDuration(3);

        course.setCourseFormat(bootcamp);*/

//        System.out.println(course);

        ManagerDao managerDao = new ManagerDaoImpl();

        Manager manager = new Manager();
        manager.setId(2);
        manager.setName("Aibek");
        manager.setSurname("Bekov");
        manager.setEmail("bek@gmail.com");
        manager.setPhone("996555666666");
        manager.setSalary(25000.0);

//        managerDao.save(manager);
        Manager[] managers = managerDao.findAll();
        System.out.println(Arrays.toString(managers));
    }
}