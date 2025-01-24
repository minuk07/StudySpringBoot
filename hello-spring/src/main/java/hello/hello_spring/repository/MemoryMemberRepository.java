package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    //HashMap은 Map 인터페이스의 구현체 . 하나. 해시 테이블을 사용하여 데이터를 저장, 빠른 조회 성능.
    private static long sequence = 0L; //키 값을 생성해.(0,1,2,,,)


    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id값 세팅
        store.put(member.getId(), member); //id, name Map에 저장.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null값이 반환될 수 있으니 null이어도 감싸서 넘김.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //name이 같은 객체가 있다면 해당 객체 반환.
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //Map의 value(Member)들을 List로 반환.
    }

    public void clearStore(){
        store.clear();
    }
}
