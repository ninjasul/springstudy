package chap16_springtest.bank.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(of="accountNo")
@Builder
public class Account {
    private String accountNo;
    private double balance;
}