package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 세 개의 테스트 케이스를 한번에 돌리면 실행 순서는 정해져있지 않기 때문에 데이터 간의 충돌로 에러가 날 수 있다.
    public void afterEach() { // 테스트가 끝날 때마다 리포지토리를 깔끔하게 지워줌
        // 각각의 테스트가 끝날 때 마다 호출됨 (콜백함수)
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member(); // 멤버 객체를 생성한다.
        member.setName("spring"); // 생성한 멤버에 이름을 정한다.

        repository.save(member); // 멤버를 저장한다. --> id는 자동으로 셋팅된다.
        Member result = repository.findById(member.getId()).get(); // 제대로 저장되어있는지 값을 꺼내어 본다. (get()으로 값을 꺼낼 수 있다.)
        // 꺼낸 값으로 제대로 저장되었는지 검증한다.
        // System.out.println("result = " + (result == member)); // 글자로 출력하여 테스트 --> 보통은 이렇게 테스트하진 않음
        // Assertions.assertEquals(member, result); // member와 result가 같은지 테스트
        Assertions.assertThat(member).isEqualTo(result); // member가 result와 같은지 테스트

        // Assertions static import 후 asserThat만 작성하여 사용 가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
