package com.company.basketstat.screen.teamtwoscorebox;

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

@UiController("bst_TeamTwoScoreBox")
@UiDescriptor("team-two-score-box.xml")
public class TeamTwoScoreBox extends AbstractTeamScoreBox {

    @Autowired
    private EntityPicker<Team> teamTwoField;
    @Autowired
    private InstancePropertyContainer<Team> teamTwoDc;
    @Autowired
    private Table<Player> teamTwoTable;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private InstanceContainer<Game> gameDc;
    @Autowired
    private CollectionContainer<PlayerGameStatistic> teamStatsDc;
    @Autowired
    private CollectionLoader<PlayerGameStatistic> teamStatsDl;
    @Autowired
    private Label<Integer> teamTwoScoreLabel;

    public void initPlayerGameStatistic() {
        Collection<PlayerGameStatistic> statistics = new ArrayList<>();
        teamTwoDc.getItem().getPlayers().forEach(player -> {
            PlayerGameStatistic playerGameStat = dataManager.create(PlayerGameStatistic.class);
            playerGameStat.setPlayer(player);
            playerGameStat.setGame(gameDc.getItem());
            statistics.add(playerGameStat);
        });
        teamStatsDc.setItems(statistics);
    }

    @Subscribe("teamTwoField")
    public void onTeamFieldValueChange(HasValue.ValueChangeEvent<Team> event) {
        Team team = teamTwoField.getValue();
        if (event.getPrevValue() == team)
            return;
        teamTwoDc.setItem(team);
    }

    @Subscribe(id = "teamTwoDc", target = Target.DATA_CONTAINER)
    public void onTeamTwoDcItemChange(InstanceContainer.ItemChangeEvent<Team> event) {
        Team team = event.getItem();
        if (team == event.getPrevItem())
            return;
        teamTwoField.setValue(team);
    }

    @Subscribe("teamTwoTable")
    public void onTeamTwoTableSelection(Table.SelectionEvent<Player> event) {
        onTeamTableAction(teamTwoTable);
    }

    @Override
    public void refreshTeamStatDataLoader(Game game) {
        teamStatsDl.setParameter("game", game);
        teamStatsDl.setParameter("players", game.getTeamTwo().getPlayers());
        teamStatsDl.load();
    }

    protected void changeScoreOn(Integer changeOn) {
        Game  game= gameDc.getItem();
        game.changeTeamTwoScoreOn(changeOn);
        teamTwoScoreLabel.setValue(game.getTeamTwoScore());
    }

    @Override
    public void setEditableForTeamField(boolean editable) {
        teamTwoField.setEditable(editable);
    }
}