package com.ninjasul.tobyspring31.user.domain;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of="id")
@Builder
//@Entity
public class User {

    private String id;
    private String name;
    private String password;
    private String email;
    private Level level;
    private int loginCount;
    private int recommendationCount;

}