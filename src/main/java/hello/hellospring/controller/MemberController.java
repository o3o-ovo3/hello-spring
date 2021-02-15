package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService; // 스프링 컨테이너에 등록

    @Autowired // 스프링 컨테이너에서 멤버 서비스를 가져온다.
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        
        return "redirect:/"; // 회원가입이 끝나면 홈 화면으로 돌려보냄
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); // 회원 목록 전체 조회
        model.addAttribute("members", members); // 모델에 회원 목록을 넣어준다.
        return "members/memberList";
    }
}
