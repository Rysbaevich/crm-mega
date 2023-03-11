package service.impl;

import dao.CourseFormatDao;
import dao.impl.CourseFormatDaoImplV2;
import model.CourseFormat;
import service.CommandService;

import java.util.Arrays;

import static enums.Format.BOOTCAMP;

public class CommandServiceImpl implements CommandService {
    @Override
    public void run() {

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

/*        ManagerDao managerDao = new ManagerDaoImpl();

        Manager manager = new Manager();
        manager.setId(2);
        manager.setName("Aibek");
        manager.setSurname("Bekov");
        manager.setEmail("bek@gmail.com");
        manager.setPhone("996555666666");
        manager.setSalary(25000.0);

        managerDao.save(manager);
        Manager[] managers = managerDao.findAll();
        System.out.println(Arrays.toString(managers));*/

//        FormatOldVersion format = new FormatOldVersion("Bootcamp");

        CourseFormat courseFormat = new CourseFormat();
        courseFormat.setFormat(BOOTCAMP);
        courseFormat.setLessonDuration(3);
        courseFormat.setOnline(true);
        courseFormat.setDurationInWeek(18);
        courseFormat.setLessonCountPerWeek(5);
        courseFormat.setId(4);

        CourseFormatDao courseFormatDao = new CourseFormatDaoImplV2();
//        courseFormatDao.save(courseFormat);
//        System.out.println(ORDINARY.ordinal());
        System.out.println(Arrays.toString(courseFormatDao.findAll()));
    }
}
