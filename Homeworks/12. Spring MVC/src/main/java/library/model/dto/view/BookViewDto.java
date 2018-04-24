package library.model.dto.view;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookViewDto implements Serializable {

    private Long id;
    private String name;
    private String author;
    private Integer year;
    private BigDecimal price;
    private String userName;
}
