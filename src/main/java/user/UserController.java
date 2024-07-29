package user;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(Connection connection) {
        this.userService = new UserService(connection);
    }

    public void addUser(User user) throws SQLException {
        userService.addUser(user);
    }

    public User getUser(int userId) throws SQLException {
        return userService.getUser(userId);
    }

    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

    public void updateUser(User user) throws SQLException {
        userService.updateUser(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userService.deleteUser(userId);
    }
}
