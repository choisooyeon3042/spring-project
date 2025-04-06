package com.estsoft.demo.service;

import com.estsoft.demo.repository.Team;
import com.estsoft.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TeamService {
    public final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, String name) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 팀을 찾을 수 없어요."));

        team.setName(name);
        return teamRepository.save(team);
    }
}