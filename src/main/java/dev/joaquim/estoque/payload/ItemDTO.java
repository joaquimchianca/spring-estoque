package dev.joaquim.estoque.payload;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    @NotBlank(message = "name must not be null or empty")
    @Min(value = 3, message = "name size must have at least 3 characters")
    private String name;

    private String description;

    @PositiveOrZero
    private Long amount;
}
