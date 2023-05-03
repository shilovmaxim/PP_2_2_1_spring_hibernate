package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        try {
            userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
            userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
            userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
            userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
            userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("Lada", 10)));
            userService.add(new User("User6", "Lastname6", "user6@mail.ru", new Car("BMW", 7)));
            userService.add(new User("User7", "Lastname7", "user7@mail.ru", new Car("BMW", 5)));
            userService.add(new User("User8", "Lastname8", "user8@mail.ru", new Car("Exeed", 5)));
            userService.add(new User("User9", "Lastname9", "user9@mail.ru", new Car("Lada", 10)));
        } catch (Exception e) {
            System.out.println("Дубликаты User в БД не записаны. Уникальность по email.");
        }
        Car car1 = new Car("Lada", 10);


        List<User> users = userService.listUsers();
        for (User user : users) {
            printUsers(user);
        }
        System.out.println("################################");
        System.out.println("Все владельцы : " + car1.getModel() + " " + car1.getSeries());
        System.out.println("################################");
        for (User user : userService.getUserCar(car1)) {
            printUsers(user);
        }
        context.close();
    }

    private static void printUsers(User user) {
        System.out.println("Id = " + user.getId());
        System.out.println("First Name = " + user.getFirstName());
        System.out.println("Last Name = " + user.getLastName());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Car = " + user.getCar());
        System.out.println("___________________________");
    }
}
