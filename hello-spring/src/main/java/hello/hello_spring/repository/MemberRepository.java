package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository { //추상메서드로만 이루어진 클래스 (interface)

    Member save(Member member);

    Optional<Member> findById(Long id); //null값을 Optional로 감싸서 반환.

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
