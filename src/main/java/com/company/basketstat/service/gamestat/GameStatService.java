package com.company.basketstat.service.gamestat;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.Player;
import com.company.basketstat.entity.PlayerGameStat;

public interface GameStatService {
    PlayerGameStat getPlayerGameStatBy(Game game, Player player);
}
