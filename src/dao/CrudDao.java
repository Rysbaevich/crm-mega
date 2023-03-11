package dao;

public interface CrudDao<Model> {
    void save(Model model);

    Model[] findAll();

    default void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
