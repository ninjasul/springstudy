package inflearn.jpa.basic;

import inflearn.jpa.basic.domain.Address;
import inflearn.jpa.basic.domain.valuetype.collection.AddressEntity;
import inflearn.jpa.basic.domain.valuetype.collection.Member;
import inflearn.jpa.basic.util.JpaUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Slf4j
public class _09_ValueTypeCollectionEntity {
    public static void main(String[] args) {
        JpaUtil.doTest(_09_ValueTypeCollectionEntity::doTest);
    }

    private static void doTest(EntityManager em) {
        Member member = new Member();
        member.setUserName("hello");
        member.setHomeAddress(new Address("Seoul", "Pangyo", "10000"));

        member.getFavoriteFoods().add("치킨");
        member.getFavoriteFoods().add("족발");
        member.getFavoriteFoods().add("피자");

        member.getAddressHistories().add(new AddressEntity("oldCity1", "oldStreet1", "99999"));
        member.getAddressHistories().add(new AddressEntity("oldCity2", "oldStreet2", "99998"));

        em.persist(member);

        em.flush();
        em.clear();

        log.info("===================== START =====================");
        Member foundMember = em.find(Member.class, member.getId());

        // 조회
        // 기본적으로 값 객체 컬렉션은 LAZY 로딩임.
        List<AddressEntity> addressHistories = foundMember.getAddressHistories();
        for (AddressEntity address : addressHistories) {
            log.info("Address: {}", address.getCity());
        }

        Set<String> favoriteFoods = foundMember.getFavoriteFoods();
        for (String favoriteFood : favoriteFoods) {
            log.info("favoriteFood: {}", favoriteFood);
        }

        // 수정
        Address oldHomeAddress = foundMember.getHomeAddress();
        foundMember.setHomeAddress(new Address("newCity", oldHomeAddress.getStreet(), oldHomeAddress.getZipcode()));

        // 치킨 -> 한식
        foundMember.getFavoriteFoods().remove("치킨");
        foundMember.getFavoriteFoods().add("한식");

        // 값 타입 컬렉션의 특정 항목을 식별할 수 없는 PK가 존재하지 않으므로
        // 삭제 시 ADDRESS 테이블에 있는 특정 멤버의 데이터를 모두 삭제 한 다음 다시 INSERT를 함.
        // 차라리 값 타입 컬렉션을 쓸거면 OneToMany 조인으로 해결하는 게 나음.
        foundMember.getAddressHistories().remove(new AddressEntity("oldCity1", "oldStreet1", "99999"));
        foundMember.getAddressHistories().add(new AddressEntity("newCity", "oldStreet1", "99999"));
    }
}