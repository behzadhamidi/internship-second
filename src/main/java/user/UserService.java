package user;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(Connection connection) {
        this.userRepository = new UserRepository(connection);
    }

    public void addUser(User user) throws SQLException {
        userRepository.addUser(user);
    }

    public User getUser(int userId) throws SQLException {
        return userRepository.getUser(userId);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    public void updateUser(User user) throws SQLException {
        userRepository.updateUser(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userRepository.deleteUser(userId);
    }
}
