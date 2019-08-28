package inflearn.jpa.basic.domain.mappedsuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "TEAM_MAPPED_BY_SUPPERCLASS")
public class Team extends BaseEntity {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(Long id, String name, List<Member> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public Team(String name, List<Member> members) {
        this.name = name;
        this.members = members;
    }
}