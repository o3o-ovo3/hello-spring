package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 테스트를 실행할 때 트랜잭션을 먼저 실행하고, DB 데이터를 저장하고 테스트가 끝나면 rollback 해준다. 따라서 DB에 남지 않게된다.
class MemberServiceIntegrationTest {

    // 스프링 컨테이너에게 멤버 서비스 멤버 리포지토리를 받는다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);
        // 저장한 것이 리포지토리에 있는지 확인하고 싶은 것

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1); // 여기까진 문제 없음

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 예외 발생 확인

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // then
    }
}