package com.company.basketstat.screen.statistic;

import com.company.basketstat.entity.PlayerGameStat;
import com.company.basketstat.entity.PlayerStatistic;
import io.jmix.core.Messages;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import com.company.basketstat.entity.Statistic;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("bst_PlayerGameStat.edit")
@UiDescriptor("statistic-edit.xml")
@EditedEntityContainer("playerStatisticDc")
public class StatisticEdit extends StandardEditor<PlayerGameStat> {
    @Autowired
    private Messages messages;
    @Autowired
    private Button reboundPlusBtn;
    @Autowired
    private Button reboundMinusBtn;
    @Autowired
    private Button assistPlusBtn;
    @Autowired
    private Button assistMinusBtn;
    @Autowired
    private Button blockPlusBtn;
    @Autowired
    private Button blockMinusBtn;
    @Autowired
    private Button foulPlusBtn;
    @Autowired
    private Button foulMinusBtn;
    @Autowired
    private Button twoPointMissPlusBtn;
    @Autowired
    private Button twoPointMissMinusBtn;
    @Autowired
    private Button twoPointMadePlusBtn;
    @Autowired
    private Button twoPointMadeMinusBtn;
    @Autowired
    private Button turnOverPlusBtn;
    @Autowired
    private Button turnOverMinusBtn;
    @Autowired
    private Button threePointMissPlusBtn;
    @Autowired
    private Button threePointMissMinusBtn;
    @Autowired
    private Button threePointMadePlusBtn;
    @Autowired
    private Button threePointMadeMinusBtn;
    @Autowired
    private Button stealPlusBtn;
    @Autowired
    private Button stealMinusBtn;
    @Autowired
    private Button freeThrowMissMinusBtn;
    @Autowired
    private Button freeThrowMissPlusBtn;
    @Autowired
    private Button freeThrowMadePlusBtn;
    @Autowired
    private Button freeThrowMadeMinusBtn;
    @Autowired
    private TextField<Integer> twoPointMissField;
    @Autowired
    private TextField<Integer> twoPointMadeField;
    @Autowired
    private TextField<Integer> turnOverField;
    @Autowired
    private TextField<Integer> threePointMissField;
    @Autowired
    private TextField<Integer> threePointMadeField;
    @Autowired
    private TextField<Integer> stealField;
    @Autowired
    private TextField<Integer> reboundField;
    @Autowired
    private TextField<Integer> freeThrowMissField;
    @Autowired
    private TextField<Integer> freeThrowMadeField;
    @Autowired
    private TextField<Integer> foulField;
    @Autowired
    private TextField<Integer> blockField;
    @Autowired
    private TextField<Integer> assistField;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        getWindow().setCaption(messages.formatMessage(getClass(),
                "StatisticEdit.caption",
                getEditedEntity().getPlayer().getInstanceName()));
        initPlusMinusButtons();
    }


    private void initPlusMinusButtons(){
        initPlusMinusBtn(freeThrowMadeMinusBtn,freeThrowMadePlusBtn,freeThrowMadeField);
        initPlusMinusBtn(freeThrowMissMinusBtn,freeThrowMissPlusBtn,freeThrowMissField);
        initPlusMinusBtn(twoPointMadeMinusBtn,twoPointMadePlusBtn,twoPointMadeField);
        initPlusMinusBtn(twoPointMissMinusBtn,twoPointMissPlusBtn,twoPointMissField);
        initPlusMinusBtn(threePointMadeMinusBtn,threePointMadePlusBtn,threePointMadeField);
        initPlusMinusBtn(threePointMissMinusBtn,threePointMissPlusBtn,threePointMissField);
        initPlusMinusBtn(turnOverMinusBtn,turnOverPlusBtn,turnOverField);
        initPlusMinusBtn(assistMinusBtn,assistPlusBtn,assistField);
        initPlusMinusBtn(reboundMinusBtn,reboundPlusBtn,reboundField);
        initPlusMinusBtn(stealMinusBtn,stealPlusBtn,stealField);
        initPlusMinusBtn(blockMinusBtn,blockPlusBtn,blockField);
        initPlusMinusBtn(foulMinusBtn,foulPlusBtn,foulField);
    }

    @SuppressWarnings("DataFlowIssue")
    private void initPlusMinusBtn(Button minusBtn, Button plusBtn, TextField<Integer> field) {
        minusBtn.addClickListener(e -> {
            if (field.getValue() != 0) {
                field.setValue(field.getValue() - 1);
            }
        });
        plusBtn.addClickListener(e -> field.setValue(field.getValue() + 1));
    }


}