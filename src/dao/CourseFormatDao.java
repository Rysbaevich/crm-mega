package dao;

import dao.impl.CourseFormatDaoImpl;
import model.CourseFormat;

public interface CourseFormatDao extends CrudDao<CourseFormat> {
    CourseFormatDao INSTANCE = new CourseFormatDaoImpl();
}
