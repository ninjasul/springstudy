package com.springframework.validator.byannotation;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnnotatedEvent {

    @NotNull @Min(0)
    private Integer id;

    @NotNull @NotEmpty
    private String title;

}