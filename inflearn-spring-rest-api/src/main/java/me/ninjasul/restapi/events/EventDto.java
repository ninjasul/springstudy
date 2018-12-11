package me.ninjasul.restapi.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class EventDto {

    private @NotEmpty String name;

    private @NotEmpty String description;

    private @NotNull LocalDateTime beginEnrollmentDateTime;

    private @NotNull LocalDateTime closeEnrollmentDateTime;

    private @NotNull LocalDateTime beginEventDateTime;

    private @NotNull LocalDateTime endEventDateTime;

    private String location;

    private @Min(0) int basePrice;

    private @Min(0) int maxPrice;

    private @Min(0) int limitOfEnrollment;
}