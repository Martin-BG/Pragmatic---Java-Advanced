package library.model.dto.binding;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookCreationDto implements Serializable {

    @Length(min = 1, max = 30)
    @NonNull
    private String name;

    @Length(min = 1, max = 30)
    @NonNull
    private String author;

    @NotNull
    @Min(0)
    private Integer year;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;
}
