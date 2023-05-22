package com.company.basketstat.screen.teamscorebox;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.Player;
import com.company.basketstat.entity.PlayerGameStatistic;
import com.company.basketstat.screen.stateditfragment.StatEditFragment;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;

@UiController("bst_AbstractTeamScoreBox")
@UiDescriptor("abstract-team-score-box.xml")
public abstract class AbstractTeamScoreBox extends ScreenFragment {
    @Autowired
    private CollectionContainer<PlayerGameStatistic> teamStatsDc;
    @Autowired
    private StatEditFragment teamStatEditFragment;

    public abstract void refreshTeamStatDataLoader(Game editedEntity);

    @Nullable
    protected PlayerGameStatistic getStatisticByPlayer(Player player) {
        return teamStatsDc.getItems()
                .stream()
                .filter(playerGameStatistic -> player.equals(playerGameStatistic.getPlayer()))
                .findAny()
                .orElse(null);
    }

    protected void onTeamTableAction(Table<Player> teamTable) {
        Player player = teamTable.getSingleSelected();
        teamStatEditFragment.setItem(getStatisticByPlayer(player));
    }

    public CollectionContainer<PlayerGameStatistic> getTeamStatsDc() {
        return teamStatsDc;
    }

    @Subscribe(id = "teamStatEditFragment", target = Target.CONTROLLER)
    protected void onChange(StatEditFragment.ScoreChangeEvent event) {
        changeScoreOn(event.getChangeOn());
    }

    protected abstract void changeScoreOn(Integer changeOn);

    public abstract void setEditableForTeamField(boolean editable);
}