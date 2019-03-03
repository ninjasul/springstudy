package ninjasul.springmvc.bootconfiguration;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
public class Person {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Person(String name) {
        this.name = name;
    }
}
