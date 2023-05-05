package com.company.basketstat.screen.game;

import com.company.basketstat.entity.*;
import com.company.basketstat.service.gamestat.GameStatService;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.core.SaveContext;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.BaseAction;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.Label;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@UiController("bst_Game.edit")
@UiDescriptor("game-edit.xml")
@EditedEntityContainer("gameDc")
public class GameEdit extends StandardEditor<Game> {
    @Autowired
    private Button commitAndCloseBtn;
    @Autowired
    private EntityPicker<Team> teamTwoField;
    @Autowired
    private EntityPicker<Team> teamOneField;
    @Autowired
    private Label<String> teamOneNameLabel;
    @Autowired
    private Label<String> teamOneScoreLabel;
    @Autowired
    private Label<String> teamTwoNameLabel;
    @Autowired
    private Label<String> teamTwoScoreLabel;
    @Autowired
    private Messages messages;
    @Autowired
    private CollectionContainer<Player> teamOneDc;
    @Autowired
    private CollectionLoader<Player> teamOneDl;
    @Autowired
    private CollectionLoader<Player> teamTwoDl;
    @Autowired
    private Button startGameBtn;
    @Autowired
    private Button closeBtn;
    @Autowired
    private Notifications notifications;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Table<Player> teamOneTable;
    @Autowired
    private CollectionContainer<PlayerGameStat> teamOneStatsDc;
    @Autowired
    private CollectionLoader<PlayerGameStat> teamOneStatsDl;
    @Autowired
    private CollectionLoader<PlayerGameStat> teamTwoStatsDl;
    @Autowired
    private Table<PlayerGameStat> teamOneStatsTable;
    @Autowired
    private Table<Player> teamTwoTable;
    @Autowired
    private CollectionContainer<PlayerGameStat> teamTwoStatsDc;
    @Autowired
    private Table<PlayerGameStat> teamTwoStatsTable;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private GameStatService gameStatService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<Game> event) {
        event.getEntity().setTeamOneScore(0);
        event.getEntity().setTeamTwoScore(0);
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (getEditedEntity().getGameStatus() != null) {
            gameStartedFieldsVisibility();
        }
        setLabelsInitialValue();
        initTeamOneField();
        initTeamTwoField();
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        GameResult gameResult = dataManager.create(GameResult.class);
        SaveContext saveContext = new SaveContext();
        Team winner = getEditedEntity().getTeamOneScore() > getEditedEntity().getTeamTwoScore() ?
                getEditedEntity().getTeamOne() : getEditedEntity().getTeamTwo();
        Team loser = getEditedEntity().getTeamOneScore() < getEditedEntity().getTeamTwoScore() ?
                getEditedEntity().getTeamOne() : getEditedEntity().getTeamTwo();
        gameResult.setGame(getEditedEntity());
        gameResult.setWinner(winner);
        gameResult.setTeamWinnerScore(winner.equals(getEditedEntity().getTeamOne()) ?
                getEditedEntity().getTeamOneScore() : getEditedEntity().getTeamTwoScore());
        gameResult.setTeamLoserFinalScore(loser.equals(getEditedEntity().getTeamOne()) ?
                getEditedEntity().getTeamOneScore() : getEditedEntity().getTeamTwoScore());
        gameResult.setLoser(loser);
        gameResult.setGame(getEditedEntity());
        saveContext.saving(gameResult);
        getEditedEntity().setGameResult(gameResult);
        getEditedEntity().setGameStatus(GameStatus.FINISHED);
        saveContext.saving(getEditedEntity());
        dataManager.save(saveContext);
    }


    @Subscribe("startGameBtn")
    public void onStartGameBtnClick(Button.ClickEvent event) {
        if (validateTeamsFields()) {
            gameStartedFieldsVisibility();
            getEditedEntity().setGameStatus(GameStatus.STARTED);
            initPlayerGameStatisticFor(getEditedEntity().getTeamOne());
            initPlayerGameStatisticFor(getEditedEntity().getTeamTwo());
        }
    }

    private void initPlayerGameStatisticFor(Team team) {
        SaveContext saveContext = new SaveContext();
        team.getPlayers().forEach(player -> {
            PlayerGameStat playerGameStat = dataManager.create(PlayerGameStat.class);
            playerGameStat.setPlayer(player);
            Statistic statistic = dataManager.create(Statistic.class);
            playerGameStat.setStatistic(statistic);
            playerGameStat.setGame(getEditedEntity());
            saveContext.saving(statistic);
            saveContext.saving(playerGameStat);
            saveContext.saving(getEditedEntity());
        });
        dataManager.save(saveContext);
    }

    private Boolean validateTeamsFields() {
        Team teamOne = teamOneField.getValue();
        Team teamTwo = teamTwoField.getValue();
        if (teamOne == null || teamTwo == null) {
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

    private void gameStartedFieldsVisibility() {
        commitAndCloseBtn.setVisible(true);
        startGameBtn.setVisible(false);
        closeBtn.setVisible(false);
        teamOneField.setEditable(false);
        teamTwoField.setEditable(false);
    }


    private void initTeamTwoField() {
        if (teamTwoField.getValue() != null) {
            teamTwoDl.setParameter("teamTwo", teamTwoField.getValue());
            teamTwoDl.load();
            teamTwoNameLabelValue(getEditedEntity().getTeamTwo().getName());
            teamTwoScoreLabelValue(getEditedEntity().getTeamTwo().getName(), getEditedEntity().getTeamTwoScore());
        }

        teamTwoField.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                getEditedEntity().setTeamTwo(e.getValue());
                teamTwoNameLabelValue(getEditedEntity().getTeamTwo().getName());
                teamTwoScoreLabelValue(getEditedEntity().getTeamTwo().getName(),
                        getEditedEntity().getTeamOneScore());
                teamTwoDl.setParameter("teamTwo", e.getValue());
                teamTwoDl.load();
            }
        });

        teamTwoTable.addSelectionListener(e -> {
            Player singleSelected = teamTwoTable.getSingleSelected();
            if (singleSelected != null) {
                teamTwoStatsDl.setParameter("teamTwoPlayer", singleSelected);
                teamTwoStatsDl.setParameter("game", getEditedEntity());
                teamTwoStatsDl.load();
            } else {
                teamTwoStatsDc.getMutableItems().clear();
                teamTwoStatsTable.repaint();
            }
        });
        teamTwoTable.setItemClickAction(new BaseAction("showEditorTeamTwo").withHandler(e -> {
            Screen editor = screenBuilders.editor(PlayerGameStat.class, this)
                    .editEntity(
                            gameStatService.getPlayerGameStatBy(getEditedEntity(), teamTwoTable.getSingleSelected()))
                    .build();
            editor.addAfterCloseListener(e1 -> {
                if (e1.closedWith(StandardOutcome.COMMIT)) {
                    teamTwoStatsDl.load();
                }
            });
            editor.show();
        }));

    }

    private void initTeamOneField() {
        if (teamOneField.getValue() != null) {
            teamOneDl.setParameter("teamOne", teamOneField.getValue());
            teamOneDl.load();
            teamOneNameLabelValue(getEditedEntity().getTeamOne().getName());
            teamOneScoreLabelValue(getEditedEntity().getTeamOne().getName(), getEditedEntity().getTeamOneScore());
        }

        teamOneField.addValueChangeListener(e -> {
            if (e.getValue() != null) {
                getEditedEntity().setTeamOne(e.getValue());
                teamOneNameLabelValue(getEditedEntity().getTeamOne().getName());
                teamOneScoreLabelValue(getEditedEntity().getTeamOne().getName(),
                        getEditedEntity().getTeamOneScore());
                teamOneDl.setParameter("teamOne", e.getValue());
                teamOneDl.load();
            }
        });
        teamOneTable.addSelectionListener(e -> {
            Player singleSelected = teamOneTable.getSingleSelected();
            if (singleSelected != null) {
                teamOneStatsDl.setParameter("teamOnePlayer", singleSelected);
                teamOneStatsDl.setParameter("game", getEditedEntity());
                teamOneStatsDl.load();
            } else {
                teamOneStatsDc.getMutableItems().clear();
                teamOneStatsTable.repaint();
            }
        });

        teamOneTable.setItemClickAction(new BaseAction("showEditorTeamOne").withHandler(e -> {
            Screen editor = screenBuilders.editor(PlayerGameStat.class, this)
                    .editEntity(gameStatService.getPlayerGameStatBy(getEditedEntity(), teamOneTable.getSingleSelected()))
                    .build();
            editor.addAfterCloseListener(e1 -> {
                if (e1.closedWith(StandardOutcome.COMMIT)) {
                    teamOneStatsDl.load();
                }
            });
            editor.show();
        }));
    }

    private void setLabelsInitialValue() {
        teamOneNameLabelValue("");
        teamTwoNameLabelValue("");
        teamOneScoreLabelValue("Команда 1", getEditedEntity().getTeamOneScore());
        teamTwoScoreLabelValue("Команда 2", getEditedEntity().getTeamTwoScore());
    }

    private void teamOneNameLabelValue(String value) {
        teamOneNameLabel.setValue(messages.formatMessage(getClass(), "teamOneNameLabel.value", value));
    }

    private void teamOneScoreLabelValue(String teamName, Integer score) {
        teamOneScoreLabel.setValue(messages.formatMessage(getClass(),
                "teamOneScoreLabel.value",
                teamName,
                score.toString()));
    }

    private void teamTwoNameLabelValue(String value) {
        teamTwoNameLabel.setValue(messages.formatMessage(getClass(), "teamTwoNameLabel.value", value));
    }

    private void teamTwoScoreLabelValue(String teamName, Integer score) {
        teamTwoScoreLabel.setValue(messages.formatMessage(getClass(),
                "teamTwoScoreLabel.value",
                teamName,
                score.toString()));
    }

}