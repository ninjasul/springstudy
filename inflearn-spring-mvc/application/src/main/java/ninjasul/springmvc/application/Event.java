package ninjasul.springmvc.application;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    private Integer id;
    private String name;
    private Integer limit;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
}