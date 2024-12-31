package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SesseionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SesseionManager sesseionManager;
    //@GetMapping("/")
    public String home() {
        return "home";
    }

   //@GetMapping("/")
    public String homeLogin(@CookieValue(name="memberId",required = false)Long memberId, Model model){
        if(memberId==null){
            return "home";
        }

        Member loginMember = memberRepository.findById(memberId); //쿠키의 멤버 ID를 통해 멤버 찾기

        if(loginMember==null){
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";
    }
    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model){

        Member member = (Member)sesseionManager.getSession(request);

        if(member==null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
}