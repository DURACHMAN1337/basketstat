package com.company.basketstat.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@JmixEntity(name = "bst_Statistic")
@MappedSuperclass
public abstract class AbstractStatistic extends BaseUUIDEntity {
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

    public Integer getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Integer turnOver) {
        this.turnOver = turnOver;
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

    public String getStat(Integer madeThrow, Integer missThrow) {
        String format = "%s $s";
        return String.format(format,
                getFieldGoalStat(madeThrow, missThrow),
                getPercentStatString(madeThrow, missThrow));
    }

    @DependsOnProperties({"freeThrowMade", "freeThrowMiss"})
    public String getFreeThrowStat() {
        return getStat(twoPointMade, twoPointMiss);
    }

    @DependsOnProperties({"twoPointMade", "twoPointMiss"})
    public String getTwoPointStat() {
        return getStat(twoPointMade, twoPointMiss);
    }

    @DependsOnProperties({"freeThrowMade", "freeThrowMiss"})
    public String getFieldGoalFreeThrow() {
        return getFieldGoalStat(freeThrowMade, freeThrowMiss);
    }

    @DependsOnProperties({"freeThrowMade", "freeThrowMiss"})
    public String getPercentFreeThrow() {
        return getPercentStatString(freeThrowMade, freeThrowMiss);
    }

    @DependsOnProperties({"twoPointMade", "twoPointMiss"})
    public String getFieldGoalTwoPointThrow() {
        return getFieldGoalStat(twoPointMade, twoPointMiss);
    }

    @DependsOnProperties({"twoPointMade", "twoPointMiss"})
    public String getPercentTwoPointThrow() {
        return getPercentStatString(twoPointMade, twoPointMiss);
    }

    @DependsOnProperties({"threePointMade", "threePointMiss"})
    public String getFieldGoalThreePointThrow() {
        return getFieldGoalStat(twoPointMade, twoPointMiss);
    }

    @DependsOnProperties({"threePointMade", "threePointMiss"})
    public String getPercentThreePointThrow() {
        return getPercentStatString(twoPointMade, twoPointMiss);
    }

    public String getFieldGoalStat(@NotNull Integer madeThrow, @NotNull Integer missThrow) {
        String format = "%d/%d";
        int totalAttempts = madeThrow + missThrow;
        return String.format(format, freeThrowMade, totalAttempts);
    }

    private String getPercentStatString(Integer madeThrow, Integer missThrow) {
        return String.format("%.2f%%", getPercentStat(madeThrow, missThrow));
    }

    public Double getPercentStat(@NotNull Integer madeThrow, @NotNull Integer missThrow) {
        double totalAttempts = madeThrow + missThrow;
        return (madeThrow / totalAttempts) * 100;
    }

}