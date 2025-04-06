package com.estsoft.demo.controller;

import com.estsoft.demo.repository.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamDTO {
    private Long teamId;
    private String name;

    public TeamDTO(Team team) {
        this.teamId = team.getId();
        this.name = team.getName();
    }
}
