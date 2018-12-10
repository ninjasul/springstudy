package me.ninjasul.databinding.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

// Converter는 ConverterRegistry 에 등록하여 사용할 수도 있음.
// spring boot에서는 그냥 빈으로 등록해서 사용하면 boot 가 ConversionService 에 알아서 등록해 줌.
public class EventConverter {

    @Component
    public static class StringToEventConverter implements Converter<String, ConverterEvent> {
        @Override
        public ConverterEvent convert(String source) {
            return new ConverterEvent(Integer.parseInt(source));
        }
    }

    @Component
    public static class EventToStringConverter implements Converter<ConverterEvent, String> {

        @Override
        public String convert(ConverterEvent source) {
            return source.getId().toString();
        }
    }
}