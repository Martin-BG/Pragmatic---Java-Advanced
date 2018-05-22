package imdb.user.service;

import imdb.user.dao.UserDao;
import imdb.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean add(User user) {
        return this.userDao.add(user);
    }

    public long getIdByEmail(String email) {
        return this.userDao.getIdByEmail(email);
    }

    public boolean areCredentialsValid(String email, String password) {
        User user = this.userDao.getUser(email);
        return user != null && user.getPassword().equals(password);
    }
}
