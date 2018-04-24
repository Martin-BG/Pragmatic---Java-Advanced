package library.persistance.repository;

import library.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByName(final String name);

    User findOneByEmail(final String email);
}
