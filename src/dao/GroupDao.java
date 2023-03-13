package dao;

import dao.impl.GroupDaoImplV2;
import model.Group;

public interface GroupDao extends CrudDao<Group> {
    GroupDao INSTANCE = new GroupDaoImplV2();
}
