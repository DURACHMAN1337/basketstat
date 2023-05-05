package com.company.basketstat.service.gamestat;

import com.company.basketstat.entity.Game;
import com.company.basketstat.entity.Player;
import com.company.basketstat.entity.PlayerGameStat;
import io.jmix.core.DataManager;
import io.jmix.core.FetchPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bst_GameStatService")
public class GameStatServiceBean implements GameStatService {
    @Autowired
    private DataManager dataManager;

    @Override
    public PlayerGameStat getPlayerGameStatBy(Game game, Player player) {
        return dataManager.load(PlayerGameStat.class)
                .query("select e from bst_PlayerGameStat e where e.game =:game and e.player =:player")
                .parameter("game", game)
                .parameter("player", player)
                .fetchPlan(FetchPlan.BASE)
                .one();
    }
}
