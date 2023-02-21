import model.Course;
import model.CourseFormat;

public class Main {
    public static void main(String[] args) {
        Course course = new Course();
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

        course.setCourseFormat(bootcamp);

        System.out.println(course);
    }
}