package com.company.basketstat.screen.player;

import io.jmix.ui.screen.*;
import com.company.basketstat.entity.Player;

@UiController("bst_Player.browse")
@UiDescriptor("player-browse.xml")
@LookupComponent("playersTable")
public class PlayerBrowse extends StandardLookup<Player> {
}