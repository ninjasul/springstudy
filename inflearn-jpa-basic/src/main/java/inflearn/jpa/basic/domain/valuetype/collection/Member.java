package inflearn.jpa.basic.domain.valuetype.collection;

import inflearn.jpa.basic.domain.Address;
import inflearn.jpa.basic.domain.Period;
import inflearn.jpa.basic.domain.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
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

    @ElementCollection
    @CollectionTable(
            name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID")
    )
    // 객체가 아니라 String 단일 값이므로 @Column 어노테이션으로 컬럼명을 지정할 수 있음.
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistories = new ArrayList<>();

    public Member(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}