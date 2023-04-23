package com.distributed_system.term_project.member.controller;

import com.distributed_system.term_project.member.dto.MemberPostDto;
import com.distributed_system.term_project.member.entity.Member;
import com.distributed_system.term_project.member.repository.MemberRepository;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;
    @GetMapping("/login")
    public String login(@RequestBody MemberPostDto memberPostDto,
        HttpSession session){
        //인증절차 x
        Member member = Member.builder().name(memberPostDto.getName())
            .password(memberPostDto.getPassword()).build();

        memberRepository.save(member);

        session.setAttribute("member",member);
        log.info(member.getName() + "님 로그인 되었습니다.");
        return "로그인 완료";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 로그아웃 시 세션의 사용자 정보 삭제
        Member member = (Member) session.getAttribute("member");

        session.removeAttribute("member");

        log.info(member.getName() + "님 로그아웃 되었습니다.");
        return "로그아웃 완료";
    }

//    @GetMapping("/login")
//    public String login(@RequestBody MemberPostDto memberPostDto,
//                                      WebSocketSession session){
//        //인증절차 x
//        Member member = Member.builder().name(memberPostDto.getName())
//            .password(memberPostDto.getPassword()).build();
//
//        memberRepository.save(member);
//
//        session.getAttributes().put("loggedIn", true);
//        session.getAttributes().put("memberId", member.getMemberId());
//
//        //웹소켓 세션에 로그인여부와 멤버아이디 저장
//        log.info(member.getName() + "님 로그인 되었습니다.");
//        return "로그인 완료";
//    }
//
//    @GetMapping("/logout")
//    public String logout(WebSocketSession session) {
//        // 로그아웃 시 로그인 여부와 멤버아이디 삭제
//        session.getAttributes().put("loggedIn", false);
//        session.getAttributes().remove("userId");
//
//        return "로그아웃 완료";
//    }

}
