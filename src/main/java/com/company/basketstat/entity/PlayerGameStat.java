package com.company.basketstat.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@JmixEntity
@Entity(name = "bst_PlayerGameStat")
public class PlayerGameStat extends BaseUUIDEntity {
    @JoinColumn(name = "GAME_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Game game;

    @JoinColumn(name = "PLAYER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @JoinColumn(name = "STATISTIC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Statistic statistic;

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}