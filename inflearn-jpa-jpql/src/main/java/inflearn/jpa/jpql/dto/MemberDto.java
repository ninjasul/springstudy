package inflearn.jpa.jpql.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Builder
public class MemberDto {
    private String userName;
    private int age;

    public MemberDto(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }
}