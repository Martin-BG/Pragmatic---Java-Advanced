package library.model.dto.binding;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto implements Serializable {

    @Length(min = 4, max = 30)
    @NonNull
    private String name;

    @Pattern(regexp = "^(?=\\w)(?:\\w+\\.?)*\\w+(?<=\\w)@(?:\\w+\\.)+\\w+$")
    @Length(min = 5, max = 30)
    @NonNull
    private String email;

    @Min(0)
    private Integer age;
}
