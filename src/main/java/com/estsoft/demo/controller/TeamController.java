package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import com.estsoft.demo.repository.Team;
import com.estsoft.demo.service.MemberService;
import com.estsoft.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final MemberService memberService;

    // 전체 팀 조회
    @ResponseBody
    @GetMapping("/teams")
    public List<TeamDTO> getTeams() {
        List<Team> teamAll = teamService.getAllTeams();
        return teamAll.stream()
                .map(TeamDTO::new)
                .toList();
    }

    // 팀 추가
    @ResponseBody
    @PostMapping("/teams")
    public TeamDTO addTeam(@RequestBody Team team) {
        Team savedTeam = teamService.saveTeam(team);
        return new TeamDTO(savedTeam);
    }

    // 팀 수정
    @ResponseBody
    @PutMapping("/teams/{id}")
    public TeamDTO updateTeam(@PathVariable Long id, @RequestBody TeamDTO teamDTO) {
        Team updatedTeam = teamService.updateTeam(id, teamDTO.getName());
        return new TeamDTO(updatedTeam);
    }

    // 팀에 멤버 추가
    @ResponseBody
    @PostMapping("/teams/{teamId}/members")
    public MemberDTO addNewMember(@PathVariable Long teamId, @RequestBody MemberDTO memberDTO) {
        Member member = memberService.saveMemberByTeamId(teamId, memberDTO.getName());
        return new MemberDTO(member);
    }
}
