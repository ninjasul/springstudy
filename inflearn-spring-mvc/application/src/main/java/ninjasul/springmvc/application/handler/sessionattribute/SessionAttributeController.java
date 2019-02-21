package ninjasul.springmvc.application.handler.sessionattribute;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/sessionattribute")
@Log4j2
public class SessionAttributeController {

    @GetMapping("/getVisitTime")
    public ResponseEntity getVisitTime(@SessionAttribute LocalDateTime visitTime ) {
        log.info( "visitTime: {}", visitTime );
        return ResponseEntity.ok(visitTime.toString());
    }
}