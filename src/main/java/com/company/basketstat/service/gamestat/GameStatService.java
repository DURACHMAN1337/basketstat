package com.company.basketstat.service.gamestat;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.Player;
import com.company.basketstat.entity.PlayerGameStatistic;

public interface GameStatService {
    PlayerGameStatistic getPlayerGameStatBy(Game game, Player player);
}
