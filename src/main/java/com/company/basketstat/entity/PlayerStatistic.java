package com.company.basketstat.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;

@JmixEntity
@Entity(name = "bst_PlayerStatistic")
public class PlayerStatistic extends BaseUUIDEntity {
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "PLAYER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Player player;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATISTIC_ID")
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
}