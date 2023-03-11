package dao;

import dao.impl.ManagerDaoImplV2;
import model.Manager;

public interface ManagerDao extends CrudDao<Manager> {
    ManagerDao INSTANCE = new ManagerDaoImplV2();
}
