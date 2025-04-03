package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/members")
    public List<Member> showMembers() {
        return memberService.getMemberAll();
    }

    @ResponseBody
    @PostMapping("/members")
    public Member saveMember(@RequestBody Member member) {
        return memberService.insertMember(member);
    }

    // GET /members/{id} 회원 단건 조회
    @ResponseBody
    @GetMapping("/members/{id}")
    public Member selectMemberById(@PathVariable Long id) {
        // findById(id) select * from where id = #id
        return memberService.selectMemberById(id);
    }
    
    // DELETE /member/{id} -> 단건 삭제
    @ResponseBody
    @DeleteMapping("/members/{id}")
    public String deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
        return "삭제 성공";
    }

    @GetMapping("/hi")
    public String htmlPage() {
        return "hi";
    }
}
