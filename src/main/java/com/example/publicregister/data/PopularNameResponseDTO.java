package com.example.publicregister.data;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PopularNameResponseDTO {

    @Id
    private String firstName;

    private Integer quantity;
}
