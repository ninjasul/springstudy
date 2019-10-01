package inflearn.jpa.web.jpashop.service;

import inflearn.jpa.web.jpashop.domain.Member;
import inflearn.jpa.web.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member().builder()
                                    .name("Kim")
                                    .build();

        // when
        Long savedId = memberService.join(member);

        // 기본적으로 테스트 클래스 위에 @Transactional 을 붙이면 각 테스트 메소드 실행 후 롤백이 일어남.
        // 따라서 memberService.join() 메소드를 호출하더라도 JPA는 롤백이 일어날 것을 이미 알고 있으므로
        // insert 쿼리가 발생하지 않게 됨.
        // insert 쿼리 까지 확인하려면 테스트 메소드 위에 @Rollback(false) 어노테이션을 삽입하면 되지만
        // 데이터 베이스에 데이터가 그대로 남게 되는 단점이 있음.
        // 그럴 때는 insert 문 호출 뒤에 em.flush() 메소드를 호출 해
        // 영속성 컨텍스트와 데이터 베이스 동기화 작업을 수행하고 메소드 종료 시
        // @Transactional 어노테이션에 따라 다시 롤백을 수행하면 됨.
        em.flush();

        // then
        assertThat(savedId).isEqualTo(member.getId());
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member().builder()
                                    .name("Kim")
                                    .build();

        Member member2 = new Member().builder()
                                    .name("Kim")
                                    .build();

        // when
        memberService.join(member1);
        memberService.join(member2);

        // then
        fail("이 코드가 실행되면 잘못된 테스트임.");
    }
}