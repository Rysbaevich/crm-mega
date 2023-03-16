import dao.*;
import model.Course;
import model.CourseFormat;
import model.Group;
import model.Student;
import util.Log;

import java.time.LocalDate;
import java.time.Month;
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

        List<Course> courses = new ArrayList<>();
        Course course = new Course();
        Course course1 = new Course();

        course.setName("java");
        course.setPrice(15000);
        CourseFormat courseFormat = courseFormatDao.findById(2L);
        course.setCourseFormat(courseFormat);

        course1.setName("flutter");
        course1.setPrice(15000);
        CourseFormat courseFormat1 = courseFormatDao.findById(1L);
        course1.setCourseFormat(courseFormat1);

        courses.add(course);
        courses.add(course1);
//        courseDao.saveAll(courses);
        System.out.println(courseDao.findAll());

    }
}