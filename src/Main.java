import static enums.Format.*;

import dao.CourseFormatDao;
import dao.impl.CourseFormatDaoImpl;
import model.CourseFormat;
import service.CommandService;
import service.impl.CommandServiceImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CommandService commandService = new CommandServiceImpl();
        commandService.run();
    }
}