package com.company.basketstat.screen.game;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.GameStatus;
import com.company.basketstat.entity.Team;
import com.company.basketstat.screen.teamonescorebox.TeamOneScoreBox;
import com.company.basketstat.screen.teamtwoscorebox.TeamTwoScoreBox;
import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.model.InstancePropertyContainer;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@UiController("bst_Game.edit")
@UiDescriptor("game-edit.xml")
@EditedEntityContainer("gameDc")
public class GameEdit extends StandardEditor<Game> {

    @Autowired
    private Notifications notifications;
    @Autowired
    private Messages messages;
    @Autowired
    private Button commitAndCloseBtn;
    @Autowired
    private Button startGameBtn;
    @Autowired
    private Button closeBtn;
    @Autowired
    private InstancePropertyContainer<Team> teamOneDc;
    @Autowired
    private InstancePropertyContainer<Team> teamTwoDc;
    @Autowired
    private TeamTwoScoreBox teamTwoScoreBox;
    @Autowired
    private TeamOneScoreBox teamOneScoreBox;
    @Autowired
    private DataContext dataContext;
    @Autowired
    private InstanceContainer<Game> gameDc;


    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        initGameDc();
        initTeamDc();
        adjustVisiblyButton();
        loadStatistic();
    }

    private void initGameDc() {
        try {
            gameDc.getItem();
        } catch (IllegalStateException e) {
            gameDc.setItem(getEditedEntity());
        }
    }

    private void loadStatistic() {
        if (isGameWasStarted()) {
            teamOneScoreBox.refreshTeamStatDataLoader(getEditedEntity());
            teamTwoScoreBox.refreshTeamStatDataLoader(getEditedEntity());
        }
    }

    private void initTeamDc() {
        teamOneDc.setItem(getEditedEntity().getTeamOne());
        teamTwoDc.setItem(getEditedEntity().getTeamTwo());
    }

    private void adjustVisiblyButton() {
        if (isGameStarted()) {
            applyGameStartedFieldsVisibility();
        }
    }

    private boolean isGameStarted() {
        return GameStatus.STARTED.equals(getEditedEntity().getGameStatus());
    }

    private boolean isGameWasStarted() {
        return GameStatus.STARTED.equals(getEditedEntity().getGameStatus())
                || GameStatus.FINISHED.equals(getEditedEntity().getGameStatus());
    }

    @Subscribe("startGameBtn")
    public void onStartGameBtnClick(Button.ClickEvent event) {
        if (validateTeamsFields()) {
            applyGameStartedFieldsVisibility();
            getEditedEntity().setGameStatus(GameStatus.STARTED);
            teamOneScoreBox.initPlayerGameStatistic();
            teamTwoScoreBox.initPlayerGameStatistic();
        }
    }

    private void applyGameStartedFieldsVisibility() {
        teamOneScoreBox.setEditableForTeamField(false);
        teamTwoScoreBox.setEditableForTeamField(false);
        commitAndCloseBtn.setVisible(true);
        startGameBtn.setVisible(false);
        closeBtn.setVisible(false);
    }

    private Boolean validateTeamsFields() {
        Team teamOne;
        Team teamTwo;
        try {
            teamOne = teamOneDc.getItem();
            teamTwo = teamTwoDc.getItem();
        } catch (IllegalStateException e) {
            showWarningNotification("emptyTeam");
            return false;
        }
        if (teamOne.equals(teamTwo)) {
            showWarningNotification("sameTeams");
            return false;
        }
        return true;
    }

    private void showWarningNotification(String type) {
        if ("sameTeams".equals(type)) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withCaption(messages.getMessage(getClass(), "warningNotification.caption"))
                    .withDescription(messages.getMessage(getClass(), type + ".message"))
                    .show();
        }
        if ("emptyTeam".equals(type)) {
            notifications.create(Notifications.NotificationType.WARNING)
                    .withCaption(messages.getMessage(getClass(), "warningNotification.caption"))
                    .withDescription(messages.getMessage(getClass(), type + ".message"))
                    .show();
        }
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        dataContext.merge(teamOneScoreBox.getTeamStatsDc().getItems());
        dataContext.merge(teamTwoScoreBox.getTeamStatsDc().getItems());
    }
}