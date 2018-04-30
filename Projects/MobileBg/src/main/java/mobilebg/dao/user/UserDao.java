package mobilebg.dao.user;

import mobilebg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(String email, String password) {
        String sql = "INSERT INTO `users` (`email`, `password`) VALUES (?, ?)";
        jdbcTemplate.update(sql, email, password);
    }

    public User getOneByEmail(String email) {
        String sql = "SELECT * FROM `users` WHERE `email` = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
    }

    public void updatePassword(String email, String password) {
        String sql = "UPDATE `users` SET `password` = ? WHERE `email` = ?";
        jdbcTemplate.update(sql, password, email);
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM `users`";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public void deleteByEmail(String email) {
        String sql = "DELETE FROM `users` WHERE `email` = ?";
        jdbcTemplate.update(sql, email);
    }
}
