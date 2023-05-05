package com.company.basketstat.entity;

import io.jmix.core.entity.annotation.SystemLevel;
import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@JmixEntity
@Entity(name = "bst_Game")
public class Game extends BaseUUIDEntity {
    @JoinColumn(name = "TEAM_ONE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team teamOne;

    @Column(name = "GAME_STATUS")
    private String gameStatus;

    @JoinColumn(name = "TEAM_TWO_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team teamTwo;

    @Column(name = "TEAM_ONE_SCORE")
    private Integer teamOneScore;

    @Column(name = "TEAM_TWO_SCORE")
    private Integer teamTwoScore;

    @JoinColumn(name = "GAME_RESULT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private GameResult gameResult;

    public GameStatus getGameStatus() {
        return gameStatus == null ? null : GameStatus.fromId(gameStatus);
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus == null ? null : gameStatus.getId();
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public Integer getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(Integer teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public Integer getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(Integer teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }
}