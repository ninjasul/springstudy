package ninjasul.springmvc.bootconfiguration;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Builder
@Entity
@XmlRootElement
public class Person {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Person(String name) {
        this.name = name;
    }
}
