package library.model.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"id", "books"})
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    private Integer age;

    @OneToMany(mappedBy = "user")
    private Set<Book> books;

    public User() {
        this.books = new HashSet<>();
    }
}
