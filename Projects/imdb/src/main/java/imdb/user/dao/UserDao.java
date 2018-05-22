package imdb.user.dao;

import imdb.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao extends NamedParameterJdbcTemplate {

    @Autowired
    public UserDao(DataSource sqlDataSource) {
        super(sqlDataSource);
    }

    public boolean add(User user) {
        String sql = "INSERT INTO `users` (`email`, `password`) VALUES (?, ?)";
        int success = getJdbcOperations().update(sql, user.getEmail(), user.getPassword());
        return success == 1;
    }
}
