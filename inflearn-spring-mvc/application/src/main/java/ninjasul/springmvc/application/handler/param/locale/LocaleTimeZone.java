package ninjasul.springmvc.application.handler.param.locale;

import lombok.*;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LocaleTimeZone {
    Locale locale;
    TimeZone timeZone;
    ZoneId zoneId;
}