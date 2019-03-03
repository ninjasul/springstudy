package ninjasul.springmvc.bootconfiguration;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class PersonFormatter implements Formatter<Person> {

    @Override
    public Person parse(String name, Locale locale) throws ParseException {
        return new Person(name);
    }

    @Override
    public String print(Person person, Locale locale) {
        return person.getName();
    }
}