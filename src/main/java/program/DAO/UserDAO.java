package program.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import program.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate){this.jdbcTemplate=jdbcTemplate;}

    public List<User> getAllUsers(){
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    public User getUser(String name){
        ArrayList<User> users = (ArrayList<User>) getAllUsers();
        for(User thisUser: users){
            if (thisUser.getUsername().equals(name))
                return thisUser;
        }
        return null;
    }

    public void save(User user){
        jdbcTemplate.update("INSERT INTO users(username, userpassword) VALUES(?, ?)", user.getUsername(), user.getUserPassword());
        jdbcTemplate.update("INSERT INTO configuration(user_id) VALUES(?)", getUser(user.getUsername()).getUser_id());
    }

    public boolean isExist(User user){
        ArrayList<User> users = (ArrayList<User>) getAllUsers();
        for(User thisUser: users){
            if(thisUser.getUsername().equals(user.getUsername()))
                return true;
        }
        return false;
    }
}
