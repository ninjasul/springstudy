package me.ninjasul.abstraction.validation._new;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class Event {
    private Integer id;

    @NotEmpty
    private String title;

    @Min(0)
    private Integer limit;

    @Email
    private String email;
}