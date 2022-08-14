package io.shanil.ipldashboard.controller;

import io.shanil.ipldashboard.model.Match;
import io.shanil.ipldashboard.model.Team;
import io.shanil.ipldashboard.repository.MatchRepository;
import io.shanil.ipldashboard.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {
    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesTeam(teamName,10));
        System.out.print(team);
        return team;
    }
    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatch(@PathVariable String teamName, @RequestParam int year){
        System.out.print(teamName+year);
        LocalDate startDate= LocalDate.of(year,1,1);
        LocalDate endDate= LocalDate.of(year+1,1,1);
        return  matchRepository.getMatchByDate(teamName,startDate,endDate);

    }

}
