package dao;

import model.Manager;

public interface ManagerDao {
    void save(Manager manager);

    Manager[] findAll();
}
