package com.company.basketstat.screen.stateditfragment;

import com.company.basketstat.entity.PlayerGameStatistic;
import io.jmix.core.common.event.Subscription;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.EventObject;
import java.util.function.Consumer;

@UiController("bst_StatEditFragment")
@UiDescriptor("stat-edit-fragment.xml")
public class StatEditFragment extends ScreenFragment {
    @Autowired
    private Button minusFreeThrowMade;
    @Autowired
    private TextField<Integer> freeThrowMadeField;
    @Autowired
    private Button plusFreeThrowMade;
    @Autowired
    private InstanceContainer<PlayerGameStatistic> playerGameStatisticDc;
    @Autowired
    private Button minusFreeThrowMiss;
    @Autowired
    private TextField<Integer> freeThrowMissField;
    @Autowired
    private Button plusFreeThrowMiss;
    @Autowired
    private Button minusTwoPointThrowMade;
    @Autowired
    private TextField<Integer> twoPointThrowMadeField;
    @Autowired
    private Button plusTwoPointThrowMade;
    @Autowired
    private Button minusTwoPointThrowMiss;
    @Autowired
    private TextField<Integer> twoPointThrowMissField;
    @Autowired
    private Button plusTwoPointThrowMiss;
    @Autowired
    private Button minusThreePointThrowMade;
    @Autowired
    private TextField<Integer> threePointThrowMadeField;
    @Autowired
    private Button plusThreePointThrowMade;
    @Autowired
    private Button minusThreePointMiss;
    @Autowired
    private TextField<Integer> threePointMissField;
    @Autowired
    private Button plusThreePointMiss;
    @Autowired
    private Button minusTurnover;
    @Autowired
    private TextField<Integer> turnOverField;
    @Autowired
    private Button plusTurnover;
    @Autowired
    private Button minusAssist;
    @Autowired
    private TextField<Integer> assistsField;
    @Autowired
    private Button plusAssist;
    @Autowired
    private Button minusRebounds;
    @Autowired
    private TextField<Integer> reboundField;
    @Autowired
    private Button plusRebounds;
    @Autowired
    private Button minusSteal;
    @Autowired
    private TextField<Integer> stealField;
    @Autowired
    private Button plusSteal;
    @Autowired
    private Button minusBlock;
    @Autowired
    private TextField<Integer> blockShotsField;
    @Autowired
    private Button plusBlock;
    @Autowired
    private Button minusFoul;
    @Autowired
    private TextField<Integer> foulField;
    @Autowired
    private Button plusFoul;

    @Subscribe
    public void onInit(InitEvent event) {
        adjustButtons();
    }

    private void adjustButtons() {
        configureScoreButtons(freeThrowMadeField, plusFreeThrowMade, minusFreeThrowMade, 1);
        configureButtons(freeThrowMissField, plusFreeThrowMiss, minusFreeThrowMiss);
        configureScoreButtons(twoPointThrowMadeField, plusTwoPointThrowMade, minusTwoPointThrowMade, 2);
        configureButtons(twoPointThrowMissField, plusTwoPointThrowMiss, minusTwoPointThrowMiss);
        configureScoreButtons(threePointThrowMadeField, plusThreePointThrowMade, minusThreePointThrowMade, 3);
        configureButtons(threePointMissField, plusThreePointMiss, minusThreePointMiss);
        configureButtons(turnOverField, plusTurnover, minusTurnover);
        configureButtons(assistsField, plusAssist, minusAssist);
        configureButtons(reboundField, plusRebounds, minusRebounds);
        configureButtons(stealField, plusSteal, minusSteal);
        configureButtons(blockShotsField, plusBlock, minusBlock);
        configureButtons(foulField, plusFoul, minusFoul);
    }

    private void configureScoreButtons(TextField<Integer> editableField, Button plusButton, Button minusButton, Integer changeOn) {
        plusButton.addClickListener(clickEvent -> {
            if (editableField.getValue() != null) {
                int value = editableField.getValue();
                editableField.setValue(++value);
                fireEvent(ScoreChangeEvent.class, new ScoreChangeEvent(this, changeOn));
            }
        });
        minusButton.addClickListener(clickEvent -> {
            if (editableField.getValue() != null) {
                int value = editableField.getValue();
                if (value > 0) {
                    editableField.setValue(--value);
                    fireEvent(ScoreChangeEvent.class, new ScoreChangeEvent(this, changeOn * -1));
                }
            }
        });
    }

    private void configureButtons(TextField<Integer> editableField, Button plusButton, Button minusButton) {
        plusButton.addClickListener(clickEvent -> {
            if (editableField.getValue() != null) {
                int value = editableField.getValue();
                editableField.setValue(++value);
            }
        });
        minusButton.addClickListener(clickEvent -> {
            if(editableField.getValue()!= null){
                int value = editableField.getValue();
                if (value > 0)
                    editableField.setValue(--value);
            }
        });
    }

    public void setItem(PlayerGameStatistic statistic) {
        playerGameStatisticDc.setItem(statistic);
    }

    public Subscription addChangeListener(Consumer<ScoreChangeEvent> listener) {
        return getEventHub().subscribe(ScoreChangeEvent.class, listener);
    }

    public static class ScoreChangeEvent extends EventObject {

        private final Integer changeOn;

        public ScoreChangeEvent(Object source, Integer changeOn) {
            super(source);
            this.changeOn = changeOn;
        }

        public Integer getChangeOn() {
            return changeOn;
        }
    }
}