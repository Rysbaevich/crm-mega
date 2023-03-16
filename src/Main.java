import dao.*;
import model.Manager;
import model.Mentor;
import util.Log;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            Log.info("Loading driver...", Main.class.getName(), Class.class.getName());
            Class.forName("org.postgresql.Driver");
            Log.info("Driver loaded", Main.class.getName(), Class.class.getName());
        } catch (ClassNotFoundException e) {
            Log.error("Driver loading failed", Main.class.getName(), Class.class.getName());
            e.printStackTrace();
        }

        ManagerDao managerDao = ManagerDao.INSTANCE;
        CourseFormatDao courseFormatDao = CourseFormatDao.INSTANCE;
        CourseDao courseDao = CourseDao.INSTANCE;
        MentorDao mentorDao = MentorDao.INSTANCE;
        AddressDao addressDao = AddressDao.INSTANCE;
        GroupDao groupDao = GroupDao.INSTANCE;
        StudentDao studentDao = StudentDao.INSTANCE;

        List<Manager> managers = new ArrayList<>();
        Manager manager = new Manager();
        manager.setName("Aigerim");
        manager.setSurname("Bekova");
        manager.setEmail("aigerim@mail.ru");
        manager.setPhone("0555555556");
        manager.setSalary(20_000);

        Manager manager1 = new Manager();
        manager1.setName("Aidai");
        manager1.setSurname("Bekova");
        manager1.setEmail("aidai@mail.ru");
        manager1.setPhone("0553555556");
        manager1.setSalary(20_000);

        Manager manager2 = new Manager();
        manager2.setName("Aizat");
        manager2.setSurname("Bekova");
        manager2.setEmail("aizat@mail.ru");
        manager2.setPhone("0554555556");
        manager2.setSalary(20_000);

        Manager manager3 = Manager.ManagerBuilder
                .builder()
                .name("Aijan")
                .surname("Aibekova")
                .build();

        Mentor mentor = Mentor.builder()
                .build();
        managers.add(manager);
        managers.add(manager1);
        managers.add(manager2);
        System.out.println(managers);
        System.out.println(managerDao.saveAll(managers));
/*      Manager manager = new Manager();
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
*/
//        System.out.println(managerDao.findById(3L));
    }
}