package com.company.basketstat.screen.statistic;

import com.company.basketstat.entity.PlayerGameStat;
import com.company.basketstat.entity.PlayerStatistic;
import io.jmix.ui.screen.*;
import com.company.basketstat.entity.Statistic;

@UiController("bst_PlayerGameStat.edit")
@UiDescriptor("statistic-edit.xml")
@EditedEntityContainer("playerStatisticDc")
public class StatisticEdit extends StandardEditor<PlayerGameStat> {
}