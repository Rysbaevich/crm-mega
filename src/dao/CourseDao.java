package dao;

import dao.impl.CourseDaoImplV2;
import model.Course;

public interface CourseDao extends CrudDao<Course> {
    CourseDao INSTANCE = new CourseDaoImplV2();
}
