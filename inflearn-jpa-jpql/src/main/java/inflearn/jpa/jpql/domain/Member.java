package inflearn.jpa.jpql.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@Getter
@Builder
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String userName;

    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(Long id, String userName, int age, Team team) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.team = team;
    }

    public Member(String userName, int age, Team team) {
        this.userName = userName;
        this.age = age;
        this.team = team;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Member{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}
