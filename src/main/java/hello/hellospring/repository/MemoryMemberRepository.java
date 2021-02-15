package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 셋팅
        store.put(member.getId(), member); // Map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환되면 optional로 감쌈
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();
        // 루프를 돌아서 Map에서 찾아 하나라도 찾으면 반환, 없으면 null이 Optional로 감싸서 반환됨
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 memeber들이 반환됨
    }

    public void clearStore(){
        store.clear(); // store을 싹 비워주는 메소드
    }
}
