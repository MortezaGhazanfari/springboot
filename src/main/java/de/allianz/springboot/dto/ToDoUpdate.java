package de.allianz.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@RequiredArgsConstructor
@ToString
public class ToDoUpdate {
    @Positive
    @NotNull
    private Long id;
    @NotBlank(message = "Name is mandatory!")
    private String title;
    @NotBlank(message = "Description is mandatory!")
    private String description;

    @NotBlank(message = "Date is mandatory!")
    private String date;

    @NotNull(message = "Status is mandatory!")
    private Boolean status=false;
}
