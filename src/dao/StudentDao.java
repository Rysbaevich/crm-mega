package dao;

import dao.impl.StudentDaoImplV2;
import model.Student;

public interface StudentDao extends CrudDao<Student> {
    StudentDao INSTANCE = new StudentDaoImplV2();
}
