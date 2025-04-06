package com.estsoft.demo.service;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.MemberRepository;
import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드만
public class MemberService {
    // bean 생성자 주입
    public final MemberRepository memberRepository;
    public final TeamRepository teamRepository;

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

    public List<Member> selectMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    // 정보 추가
    public Member saveMemberByTeamId(Long teamId, String name) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("팀을 찾을 수 없어요."));

        Member member = new Member(null, name, team);
        return memberRepository.save(member);
    }
}
