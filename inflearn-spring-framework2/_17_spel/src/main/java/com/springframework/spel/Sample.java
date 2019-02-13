package com.springframework.spel;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Sample {
    private int data = 9999;
    private String message = "Hello Sample";
}