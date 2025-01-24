package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //회원 가입
    public Long join(Member member){
        //중복이름 불가
//        Optional<Member> result = memberRepository.findByName(member.getName());  //cmd+option+V : 자동 반환
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다. "); //Ctrl+T Extract Method
//        }); //같은 이름이 존재한다면

        validateDuplicateMember(member); //같은 이름이 존재한다면

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())  //cmd+option+V : 자동 반환
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다. ");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOneMember(Long memberId){
        return memberRepository.findById(memberId);
    }
}
