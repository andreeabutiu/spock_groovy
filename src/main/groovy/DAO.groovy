public class User {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
}

public interface UserDao {
    User save(User user);
    boolean delete(int id);
    User get(String email);
}

public class UserService {
    private UserDao dao;

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

    public boolean registerUser(User user) {
        boolean saved = false;
        if(dao.get(user.getEmail()) == null) {
            dao.save(user);
            saved = true;
        }
        return saved;
    }

    // ...
}
