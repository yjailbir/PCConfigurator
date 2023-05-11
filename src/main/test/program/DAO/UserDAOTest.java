package program.DAO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import program.config.SpringConfig;
import program.model.User;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringConfig.class)
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    User testUserAdmin = new User("admin", "adminPassword");

    @Test
    public void getAllUsers() {
        List<User> users = userDAO.getAllUsers();

        for (User u: users) {
            System.out.println(u.getUsername() + " " + u.getUserPassword());
        }
    }

    @Test
    public void getUser() {
        User user = userDAO.getUser("admin");

        Assert.assertEquals(user.getUsername(), testUserAdmin.getUsername());
        Assert.assertEquals(user.getUserPassword(), testUserAdmin.getUserPassword());
    }

    @Test
    public void save() {
        userDAO.save(new User("testUsername", "testPassword"));
    }

    @Test
    public void isExist() {
        Assert.assertTrue(userDAO.isExist(testUserAdmin));
    }
}