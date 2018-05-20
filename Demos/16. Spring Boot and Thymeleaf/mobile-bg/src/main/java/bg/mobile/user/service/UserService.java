package bg.mobile.user.service;

import bg.mobile.user.dao.UserDao;
import bg.mobile.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao dao;

    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public boolean add(User user) {
        return dao.add(user);
    }

    public long getIdByEmail(String email) {
        return dao.getIdByEmail(email);
    }

    public boolean areCredentialsValid(String email, String password) {
        User user = dao.getUser(email);
        return user != null && user.getPassword().equals(password);
    }

}
