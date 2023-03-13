package dao;

import dao.impl.MentorDaoImplV2;
import model.Mentor;

public interface MentorDao extends CrudDao<Mentor> {
    MentorDao INSTANCE = new MentorDaoImplV2();
}
