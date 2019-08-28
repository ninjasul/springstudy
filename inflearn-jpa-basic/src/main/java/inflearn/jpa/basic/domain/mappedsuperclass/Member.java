package inflearn.jpa.basic.domain.mappedsuperclass;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "TEAM_MAPPED_BY_SUPPERCLASS")
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member(Long id, String name, Team team, String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Member(String name, Team team, String createdBy, LocalDateTime createdDate, String modifiedBy, LocalDateTime lastModifiedDate) {
        this.name = name;
        this.team = team;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }
}