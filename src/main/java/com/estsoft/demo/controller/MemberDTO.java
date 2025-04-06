package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDTO {
    public Long id;
    public String name;
    public TeamDTO teamDTO;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.name = member.getName();

        if (member.getTeam() != null) {
            this.teamDTO = new TeamDTO(member.getTeam());
        } else {
            this.teamDTO = null;
        }
    }
}
