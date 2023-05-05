package com.company.basketstat.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@JmixEntity
@Entity(name = "bst_Statistic")
public class Statistic extends BaseUUIDEntity {
    @Column(name = "FREE_THROW_MADE")
    private Integer freeThrowMade = 0;

    @Column(name = "TURN_OVER")
    private Integer turnOver = 0;

    @Column(name = "FREE_THROW_MISS")
    private Integer freeThrowMiss = 0;

    @Column(name = "TWO_POINT_MADE")
    private Integer twoPointMade = 0;

    @Column(name = "TWO_POINT_MISS")
    private Integer twoPointMiss = 0;

    @Column(name = "THREE_POINT_MADE")
    private Integer threePointMade = 0;

    @Column(name = "THREE_POINT_MISS")
    private Integer threePointMiss = 0;

    @Column(name = "ASSIST")
    private Integer assist = 0;

    @Column(name = "REBOUND")
    private Integer rebound = 0;

    @Column(name = "STEAL")
    private Integer steal = 0;

    @Column(name = "BLOCK")
    private Integer block = 0;

    @Column(name = "FOUL")
    private Integer foul = 0;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "statistic")
    private PlayerStatistic playerStatistic;

    public Integer getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Integer turnOver) {
        this.turnOver = turnOver;
    }

    public PlayerStatistic getPlayerStatistic() {
        return playerStatistic;
    }

    public void setPlayerStatistic(PlayerStatistic playerStatistic) {
        this.playerStatistic = playerStatistic;
    }

    public Integer getFoul() {
        return foul;
    }

    public void setFoul(Integer foul) {
        this.foul = foul;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public Integer getSteal() {
        return steal;
    }

    public void setSteal(Integer steal) {
        this.steal = steal;
    }

    public Integer getRebound() {
        return rebound;
    }

    public void setRebound(Integer rebound) {
        this.rebound = rebound;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
    }

    public Integer getThreePointMiss() {
        return threePointMiss;
    }

    public void setThreePointMiss(Integer threePointMiss) {
        this.threePointMiss = threePointMiss;
    }

    public Integer getThreePointMade() {
        return threePointMade;
    }

    public void setThreePointMade(Integer threePointMade) {
        this.threePointMade = threePointMade;
    }

    public Integer getTwoPointMiss() {
        return twoPointMiss;
    }

    public void setTwoPointMiss(Integer twoPointMiss) {
        this.twoPointMiss = twoPointMiss;
    }

    public Integer getTwoPointMade() {
        return twoPointMade;
    }

    public void setTwoPointMade(Integer twoPointerMade) {
        this.twoPointMade = twoPointerMade;
    }

    public Integer getFreeThrowMiss() {
        return freeThrowMiss;
    }

    public void setFreeThrowMiss(Integer freeThrowMiss) {
        this.freeThrowMiss = freeThrowMiss;
    }

    public Integer getFreeThrowMade() {
        return freeThrowMade;
    }

    public void setFreeThrowMade(Integer freeThrowMade) {
        this.freeThrowMade = freeThrowMade;
    }
}