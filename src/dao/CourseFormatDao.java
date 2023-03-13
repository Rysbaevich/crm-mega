package dao;

import dao.impl.CourseFormatDaoImplV2;
import model.CourseFormat;

public interface CourseFormatDao extends CrudDao<CourseFormat> {
    CourseFormatDao INSTANCE = new CourseFormatDaoImplV2();
}
