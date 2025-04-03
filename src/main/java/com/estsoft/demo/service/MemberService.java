package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드만
public class MemberService {
    // bean 생성자 주입
    public final MemberRepository memberRepository;

    // getMemberAll 멤버 정보를 모두 조회
    public List<Member> getMemberAll() {
        return memberRepository.findAll();
    }

    // member 테이블에 insert 쿼리
    public Member insertMember(Member member) {
        Member savedMember = memberRepository.save(member);
        return savedMember;
    }

    // 회원 단건 조회 메서드 추가
    public Member selectMemberById(Long id) {
        Optional<Member> optMember = memberRepository.findById(id);
        return optMember.orElse(new Member());
    }

    // 회원 삭제 추가
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
