package com.company.basketstat.service.gamestat.report;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.PlayerGameStatistic;
import com.company.basketstat.entity.Team;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service(TeamGameReportWorker.NAME)
public class TeamGameReportWorkerBean implements TeamGameReportWorker {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    @Autowired
    private DataManager dataManager;

    @Override
    public List<Map<String, Object>> generateHeader(Map<String, Object> params) {
        Map<String, Object> header = new HashMap<>();
        Team team = (Team) params.get("team");
        Game game = (Game) params.get("game");
        header.put("header", getHeaderLine(game, team));
        return List.of(header);
    }

    private Object getHeaderLine(Game game, Team team) {
        return "Команда : " + team.getName() + " от " +  simpleDateFormat.format(game.getCreatedDate());
    }

    @Override
    public List<Map<String, Object>> generateTable(Map<String, Object> params) {
        List<Map<String, Object>> report = new ArrayList<>();
        List<PlayerGameStatistic> teamStatistic = loadStatistics(params);
        for (PlayerGameStatistic playerGameStat : teamStatistic) {
            report.add(getLine(playerGameStat));
        }

        return report;
    }

    private Map<String, Object> getLine(PlayerGameStatistic playerGameStat) {
        Map<String, Object> line = new HashMap<>();
        line.put("name", playerGameStat.getPlayer().getName());
        line.put("freeThrow", playerGameStat.getFreeThrowStat());
        line.put("twoPoint", playerGameStat.getTwoPointStat());
        line.put("threePoint", playerGameStat.getThreePointStat());
        line.put("rebound", playerGameStat.getRebound().toString());
        line.put("assist", playerGameStat.getAssist().toString());
        line.put("turnOver", playerGameStat.getTurnOver().toString());
        line.put("foul", playerGameStat.getFoul().toString());
        return line;
    }

    private List<PlayerGameStatistic> loadStatistics(Map<String, Object> params) {
        Team team = (Team) params.get("team");
        Game game = (Game) params.get("game");
        return dataManager.load(PlayerGameStatistic.class)
                .query("e.game.id = ?1 and e.player.team.id = ?2 ", game.getId(), team.getId())
                .list();
    }
}
