package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // JPA는 EntityManager라는 것으로 모든 게 동작된다.
    // 스프링 부트가 자동으로 만들어준 것을 injection 하면 된다.
    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 이렇게 하면 JPA가 insert 쿼리 다 만들어서 set id까지 다 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // select 해준다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPql이라는 쿼리 언어
        // 객체(멤버 엔티티)를 대상으로 쿼리를 날린다.
                                        // 객체 자체를 select
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}