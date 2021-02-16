package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    // 리포지토리를 외부에서 넣어주도록 한다. (동일한 인스턴스를 계속 참조하기 위해)
    // Dependency Injection

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    // 회원 가입
    public Long join(Member member){
        long start = System.currentTimeMillis();

        try {
            // 같은 이름이 있는 중복 회원은 가입 불가능하다는 조건
            validateDuplicateMember(member); // 중복 회원 검증

            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> { // 중복된 값이 있으면
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); // return 값이 Optional이기 때문에 가능하다. (null을 감쌌기 때문에)
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 id로 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
