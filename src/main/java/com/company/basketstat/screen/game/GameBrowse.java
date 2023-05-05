package com.company.basketstat.screen.game;

import io.jmix.ui.screen.*;
import com.company.basketstat.entity.Game;

@UiController("bst_Game.browse")
@UiDescriptor("game-browse.xml")
@LookupComponent("gamesTable")
public class GameBrowse extends StandardLookup<Game> {
}