package ninjasul.springmvc.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    private Integer id;

    @NotEmpty
    private String name;

    @Min(0)
    private Integer limit;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
}