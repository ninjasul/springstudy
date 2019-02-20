package ninjasul.springmvc.application.handler.validated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidatedEvent {

    interface ValidateName {}
    interface ValidateLimit {}

    private Integer id;

    @NotEmpty(groups= ValidateName.class)
    private String name;

    @Min(groups=ValidateLimit.class, value=0)
    private Integer limit;

}