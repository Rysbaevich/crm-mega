import dao.ManagerDao;
import model.Manager;

import java.util.Scanner;

public class Main {

//    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
//            log.info("Connecting...");
            System.out.println("Loading driver...");
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded");
//            log.info("Connection succeed");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver loading failed");
            e.printStackTrace();
        }

        ManagerDao managerDao = ManagerDao.INSTANCE;
        Manager manager = new Manager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        manager.setName(scanner.nextLine());

        System.out.println("Surname: ");
        manager.setSurname(scanner.nextLine());

        System.out.println("Phone: ");
        manager.setPhone(scanner.nextLine());

        System.out.println("Email: ");
        manager.setEmail(scanner.nextLine());

        System.out.println("Salary: ");
        manager.setSalary(scanner.nextDouble());

        managerDao.save(manager);
    }
}