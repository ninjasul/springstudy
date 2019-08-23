package inflearn.jpa.basic.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@Builder
public class Member {
    @Id
    private Long id;
    private String name;

    public Member(String name) {
        this.name = name;
    }
}