package com.company.basketstat.screen.statistic;

import com.company.basketstat.entity.PlayerGameStatistic;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;

@UiController("bst_PlayerGameStatistic.edit")
@UiDescriptor("player-game-statistic-edit.xml")
@EditedEntityContainer("playerGameStatisticDc")
public class PlayerGameStatisticEdit extends StandardEditor<PlayerGameStatistic> {
}