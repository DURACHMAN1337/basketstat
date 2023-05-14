package com.company.basketstat.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "PLAYER_GAME_STATISTIC")
@JmixEntity
@Entity(name = "bst_PlayerGameStatistic")
public class PlayerGameStatistic extends AbstractStatistic {
    @JoinColumn(name = "PLAYER_ID", nullable = false)
    @NotNull
    @ManyToOne(optional = false)
    private Player player;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "GAME_ID", nullable = false)
    @NotNull
    @ManyToOne(optional = false)
    private Game game;

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