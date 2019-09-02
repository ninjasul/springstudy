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
@NamedQuery(
        name = "Member.findByUserName",
        query = "select m from Member m where m.userName = :userName"

)
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String userName;

    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    public Member(Long id, String userName, int age, Team team, MemberType type) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        this.team = team;
        this.type = type;
    }

    public Member(String userName, int age, Team team, MemberType type) {
        this.userName = userName;
        this.age = age;
        this.team = team;
        this.type = type;
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

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
