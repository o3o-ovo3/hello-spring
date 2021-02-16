package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean // 빈으로 등록할거야
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 리포지토리와 엮어주어야 한다.
    }

    @Bean
    public MemberRepository memberRepository(){

        // return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
