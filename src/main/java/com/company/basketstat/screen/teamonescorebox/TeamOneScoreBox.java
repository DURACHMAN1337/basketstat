package com.company.basketstat.screen.teamonescorebox;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.Player;
import com.company.basketstat.entity.PlayerGameStatistic;
import com.company.basketstat.entity.Team;
import com.company.basketstat.screen.teamscorebox.AbstractTeamScoreBox;
import io.jmix.core.DataManager;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.Label;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.model.InstancePropertyContainer;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

@UiController("bst_TeamOneScoreBox")
@UiDescriptor("team-one-score-box.xml")
public class TeamOneScoreBox extends AbstractTeamScoreBox {

    @Autowired
    private EntityPicker<Team> teamOneField;
    @Autowired
    private InstancePropertyContainer<Team> teamOneDc;
    @Autowired
    private Table<Player> teamOneTable;
    @Autowired
    private InstanceContainer<Game> gameDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionContainer<PlayerGameStatistic> teamStatsDc;
    @Autowired
    private CollectionLoader<PlayerGameStatistic> teamStatsDl;
    @Autowired
    private Label<Integer> teamOneScoreLabel;

    public void initPlayerGameStatistic() {
        Collection<PlayerGameStatistic> statistics = new ArrayList<>();
        teamOneDc.getItem().getPlayers().forEach(player -> {
            PlayerGameStatistic playerGameStat = dataManager.create(PlayerGameStatistic.class);
            playerGameStat.setPlayer(player);
            playerGameStat.setGame(gameDc.getItem());
            statistics.add(playerGameStat);
        });
        teamStatsDc.setItems(statistics);
    }

    @Subscribe("teamOneField")
    public void onTeamFieldValueChange(HasValue.ValueChangeEvent<Team> event) {
        Team team = teamOneField.getValue();
        if (event.getPrevValue() == team)
            return;
        teamOneDc.setItem(team);
    }

    @Subscribe(id = "teamOneDc", target = Target.DATA_CONTAINER)
    public void onTeamOneDcItemChange(InstanceContainer.ItemChangeEvent<Team> event) {
        Team team = event.getItem();
        if (team == event.getPrevItem())
            return;
        teamOneField.setValue(team);
    }

    @Subscribe("teamOneTable")
    public void onTeamOneTableSelection(Table.SelectionEvent<Player> event) {
        onTeamTableAction(teamOneTable);
    }

    @Override
    public void refreshTeamStatDataLoader(Game game) {
        teamStatsDl.setParameter("game", game);
        teamStatsDl.setParameter("players", game.getTeamOne().getPlayers());
        teamStatsDl.load();
    }

    protected void changeScoreOn(Integer changeOn) {
        Game  game= gameDc.getItem();
        game.changeTeamOneScoreOn(changeOn);
        teamOneScoreLabel.setValue(game.getTeamOneScore());
    }

    @Override
    public void setEditableForTeamField(boolean editable) {
        teamOneField.setEditable(editable);
    }

}