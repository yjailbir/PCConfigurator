package program.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class User {

    private int user_id;

    @NotEmpty(message = "Логин не может быть пустым")
    @Size(min = 3, max = 20, message = "Логин может быть в длину от 3 до 20 символов")
    private String username;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 5, max = 15, message = "Пароль может быть в длину от 5 до 15 символов")
    private String userPassword;

    public User(){}

    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }

    /*public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }*/
}
