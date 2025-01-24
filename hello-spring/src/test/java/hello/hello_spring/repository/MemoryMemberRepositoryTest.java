package hello.hello_spring.repository;


import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*; //Assertions 안쓰고 바로 AssertThat으로 활용 가능

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //Test 메서드 하나하나가 끝날때마다 이 메서드가 실행
    public void afterEach(){
        repository.clearStore(); //Test 케이스가 하나 끝날때마다 데이터를 비워줘서 서로 의존성을 없애줌.
    }

    @Test //save 메소드 실행 가능 Main method 처럼 사용하면 됨
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional에서 값을 꺼내는 방법
        //System.out.println("result = " + (result == member)); //result와 member와 값이 같으면 검증 완료.
        //Assertions.assertEquals(result,member); //계속 글자로 볼 수 없으니 Assert 기능 사용해 둘이 같인지 확인. 잘 실행되면 똑같은 것.
        assertThat(member).isEqualTo(result); //Assert를 사용하는 두번째 방법(요즘 많이 사용)
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
