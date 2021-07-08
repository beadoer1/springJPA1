package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;


    @Test
//    @Rollback(false) // 넣어줘야 insert 쿼리가 나감. Test에서는 자동으로 Rollback 해버린다.(true가 기본값)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("Son");

        //when
        Long savedId = memberService.join(member);

        //then
//        em.flush(); // insert는 나간다. but, @Transactional 이 이후에 Rollback 으로 땡김.
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalStateException.class) // try-catch 를 사용하지 않아도 된다. (예상 결과를 입력)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("Son");

        Member member2 = new Member();
        member2.setName("Son");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("여기까지 오면 안돼!!!ㅋㅋㅋ");
    }

}