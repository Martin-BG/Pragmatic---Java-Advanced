package mobilebg.Terminal;

import mobilebg.dao.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Terminal implements CommandLineRunner {

    private final UserDao userDao;

    @Autowired
    public Terminal(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(final String... args) {
        userDemo();
    }

    private void userDemo() {
        System.out.println("============= CLEAR PREVIOUS USERS RECORDS (if any) =============");
        this.userDao.getAll().forEach(user -> this.userDao.deleteByEmail(user.getEmail()));

        List<String> emails = new ArrayList<String>() {{
            add("Koko@abv.bg");
            add("Koko1@abv.bg");
            add("Koko2@abv.bg");
            add("Koko3@abv.bg");
            add("Koko4@abv.bg");
        }};

        System.out.println("============= CREATE USERS =============");
        emails.forEach(email -> this.userDao.create(email, "dodo"));

        System.out.println("============= PRINT ALL USERS =============");
        this.userDao.getAll().forEach(System.out::println);

        System.out.println("============= CHANGE PASSWORDS =============");
        this.userDao.getAll().forEach(user -> this.userDao.updatePassword(user.getEmail(), "mojo"));
        this.userDao.getAll().forEach(System.out::println);

        System.out.println("============= DELETE USERS =============");
        emails.forEach(this.userDao::deleteByEmail);

        System.out.println("============= PRINT USERS (should be empty) =============");
        this.userDao.getAll().forEach(System.out::println);
    }

}
