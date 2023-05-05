package com.company.basketstat.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity
@Entity(name = "bst_GameResult")
public class GameResult extends BaseUUIDEntity {
    @JoinColumn(name = "WINNER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team winner;

    @Column(name = "TEAM_ONE_FINAL_SCORE")
    private Integer teamWinnerFinalScore;

    @Column(name = "TEAM_TWO_FINAL_SCORE")
    private Integer teamLoserFinalScore;

    @JoinColumn(name = "LOSER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team loser;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "gameResult")
    private Game game;

    public Integer getTeamLoserFinalScore() {
        return teamLoserFinalScore;
    }

    public void setTeamLoserFinalScore(Integer teamLoserFinalScore) {
        this.teamLoserFinalScore = teamLoserFinalScore;
    }

    public Integer getTeamWinnerFinalScore() {
        return teamWinnerFinalScore;
    }

    public void setTeamWinnerScore(Integer teamWinnerFinalScore) {
        this.teamWinnerFinalScore = teamWinnerFinalScore;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Team getLoser() {
        return loser;
    }

    public void setLoser(Team loser) {
        this.loser = loser;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    @InstanceName
    @DependsOnProperties({"teamWinnerFinalScore", "winner", "teamLoserFinalScore", "loser"})
    public String getInstanceName() {
        return String.format("%s - %s : %s - %s", winner.getName(), teamWinnerFinalScore, loser.getName(), teamLoserFinalScore);
    }
}