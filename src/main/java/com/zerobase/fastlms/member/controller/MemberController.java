package com.zerobase.fastlms.member.controller ;


import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @RequestMapping("/member/login")
    public String login() {

        return "member/login";  // 로그인 페이지를 반환
    }



    @GetMapping("/member/register")
    public String register(){

        return "member/register";
    }


    //request WEB ->SERVER
    //response SERVER ->WEB
    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response, MemberInput memberInput){


        /*
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        /member/register 에서 post방식으로 입력받은 정보를 직접 매핑한다. 이 방법보다는 MemberInput이라는 모델 클래스를 만들어
        자동으로 매핑하는 방법이 더 쉽다.
         */
        System.out.println(memberInput.toString());

        /* 컨트롤러에서 처리하기보다 서비스단에서 처리할 수 있다.
        Member member = new Member();
        member.setUserId(memberInput.getUserId());
        member.setUserName(memberInput.getUserName());
        member.setPhone(memberInput.getPhone());
        member.setPassword(memberInput.getPassword());
        member.setRegDt(LocalDateTime.now());
        memberRepository.save(member);
        /*
         */
        boolean result = memberService.register(memberInput);
        model.addAttribute("result", result);
        System.out.println(result);
        return "member/register_complete";
    }

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){
        String uuid = request.getParameter("id");

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "/member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo(){

        return "/member/info";
    }
}
