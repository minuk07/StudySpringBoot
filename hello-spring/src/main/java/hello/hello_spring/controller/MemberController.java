package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//스프링이 처음 뜰 때 스프링 컨테이너가 생김.
@Controller //MemberController로 객체를 생성해서 스프링에 넣어두고 스프링이 관리.(빈으로 만들어줌)
public class MemberController {

    //private final MemberService memberService = new MemberService(); //여러 컨트롤러에서 MemberService를 사용할 수 있는데 그때마다 객체가 생성됨. -> 하나만 생성하고 공용으로 사용해야 좋음.
    private final MemberService memberService;

    @Autowired //스프링이 스프링 컨테이너에 있는 MemberService를 가져다가 연결시켜줌.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //회원가입
    public String createForm(){
        return "members/createMemberForm"; //members/createMembersForm으로 이동
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //홈 화면으로 돌아가기
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        //templates/members/memberList.html로 가서 "members"에 members 랜더링

        return "members/memberList";
    }

}
