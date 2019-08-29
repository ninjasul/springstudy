package inflearn.jpa.basic;

import inflearn.jpa.basic.domain.Address;
import inflearn.jpa.basic.domain.Member;
import inflearn.jpa.basic.domain.Period;
import inflearn.jpa.basic.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
public class _06_EmbeddedType {
    public static void main(String[] args) {
        JpaUtil.doTest(_06_EmbeddedType::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member = new Member();
        member.setUserName("hello");
        member.setHomeAddress(new Address("Seoul", "Pangyo", "10000"));
        member.setOfficeAddress(new Address("Sungnam", "Sampyoung", "20000"));
        member.setWorkPeriod(new Period(LocalDate.of(2017, 1, 9), LocalDate.of(2019, 12, 31)));

        em.persist(member);
    }
}