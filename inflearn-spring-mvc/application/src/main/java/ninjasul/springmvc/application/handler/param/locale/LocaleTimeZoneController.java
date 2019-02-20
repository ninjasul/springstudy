package ninjasul.springmvc.application.handler.param.locale;

import lombok.extern.log4j.Log4j2;
import ninjasul.springmvc.application.handler.param.ParamRestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@ParamRestController
@Log4j2
public class LocaleTimeZoneController {

    @RequestMapping(
            value="/localetimezone",
            produces= MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity handleLocaleAndTimeZone(Locale locale, TimeZone timeZone, ZoneId zoneId ) {

        log.info("Locale: {}", locale );
        log.info("TimeZone: {}", timeZone );
        log.info("ZoneId: {}", zoneId );

        LocaleTimeZone localeTimeZone = LocaleTimeZone.builder()
                                                    .locale(locale)
                                                    .timeZone(timeZone)
                                                    .zoneId(zoneId)
                                                    .build();

        //log.info("Locale and TimeZone: {}", localeTimeZone);

        return ResponseEntity.ok()
                .body(localeTimeZone);
    }

}