package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // id로 member를 찾기
    Optional<Member> findByName(String name); // name으로 찾기
    List<Member> findAll(); // 모든 List 반환
}
