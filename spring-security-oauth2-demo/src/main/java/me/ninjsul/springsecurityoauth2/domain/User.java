package me.ninjsul.springsecurityoauth2.domain;

import lombok.*;
import me.ninjsul.springsecurityoauth2.domain.enums.SocialType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor @AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(of="idx")
@Entity
public class User implements Serializable {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;

    private String name;

    private String email;

    private String principal;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}