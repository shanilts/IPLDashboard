package io.shanil.ipldashboard.repository;

import io.shanil.ipldashboard.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match,Long> {
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("select m from Match m where (m.team1=:teamName or m.team2=:teamName) and date between :startDate and :endDate order by date desc")
    List<Match> getMatchByDate(@RequestParam String teamName,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate);
//    default List<Match> getMatchesByTeamBetweenDates(String teamName, LocalDate startDate, LocalDate endDate) {
//     return   getByTeam1OrTeam2OrderByDateDesc(teamName,teamName, PageRequest.of(0,4));
//    }


    public default List<Match> findLatestMatchesTeam(String teamName, int i){
        return  getByTeam1OrTeam2OrderByDateDesc(teamName,teamName,PageRequest.of(0,i));

    };
}
