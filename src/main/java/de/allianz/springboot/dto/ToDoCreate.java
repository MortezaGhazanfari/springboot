package de.allianz.springboot.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class ToDoCreate {

    @NotBlank(message = "Name is mandatory!")
    private String title;
    @NotBlank(message = "Description is mandatory!")
    private String description;

    @NotBlank(message = "Date is mandatory!")
    private String date;

}
