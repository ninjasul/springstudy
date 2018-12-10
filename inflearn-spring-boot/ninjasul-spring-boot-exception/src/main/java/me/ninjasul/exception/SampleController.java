package me.ninjasul.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping("/5XX")
    public String _5XX() {
        throw new RuntimeException();
    }

    @GetMapping("/hello")
    public String hello() {
        throw new SampleException("Exception Test");
    }

    // @ExceptionHandler(SampleException.class): SampleException 이 발생할 경우 해당 Exception에 대한 처리를 담당하겠다고 선언하는 어노테이션
    @ExceptionHandler(SampleException.class)
    public @ResponseBody AppError sampleError(SampleException e) {
        AppError appError = new AppError();
        appError.setMessage(e.getMessage());
        appError.setReason("IDK IDK IDK");
        return appError;
    }
}