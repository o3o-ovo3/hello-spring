package hello.hellospring;

import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean // 빈으로 등록할거야
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 리포지토리와 엮어주어야 한다.
    }

    @Bean
    public MemoryMemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
