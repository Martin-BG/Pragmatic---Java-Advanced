package library.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"user"})
@EqualsAndHashCode(exclude = {"id", "user"})
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String author;

    private Integer year;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
