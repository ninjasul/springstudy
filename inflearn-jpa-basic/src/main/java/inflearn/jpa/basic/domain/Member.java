package inflearn.jpa.basic.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String userName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Embedded
    private Period workPeriod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column=@Column(name = "HOME_CITY")),
            @AttributeOverride(name = "street", column=@Column(name = "HOME_STREET")),
            @AttributeOverride(name = "zipcode", column=@Column(name = "HOME_ZIPCODE"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column=@Column(name = "OFFICE_CITY")),
            @AttributeOverride(name = "street", column=@Column(name = "OFFICE_STREET")),
            @AttributeOverride(name = "zipcode", column=@Column(name = "OFFICE_ZIPCODE"))
    })
    private Address officeAddress;

    public Member(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}