package com.company.basketstat.screen.player;

import io.jmix.ui.screen.*;
import com.company.basketstat.entity.Player;

@UiController("bst_Player.edit")
@UiDescriptor("player-edit.xml")
@EditedEntityContainer("playerDc")
public class PlayerEdit extends StandardEditor<Player> {
}