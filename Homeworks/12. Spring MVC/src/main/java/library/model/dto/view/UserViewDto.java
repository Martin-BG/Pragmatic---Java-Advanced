package library.model.dto.view;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserViewDto implements Serializable {

    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Integer currentBooks;
}
