package com.company.basketstat.service.gamestat;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.Player;
import com.company.basketstat.entity.PlayerGameStatistic;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bst_GameStatService")
public class GameStatServiceBean implements GameStatService {
    @Autowired
    private DataManager dataManager;

    @Override
    public PlayerGameStatistic getPlayerGameStatBy(Game game, Player player) {
        return dataManager.load(PlayerGameStatistic.class)
                .query("select e from bst_PlayerGameStatistic e where e.game =:game and e.player =:player")
                .parameter("game", game)
                .parameter("player", player)
                .fetchPlan(FetchPlan.BASE)
                .one();
    }
}
