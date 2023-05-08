package com.company.basketstat.screen.mainscreentopmenu;

import com.company.basketstat.screen.game.GameBrowse;
import com.company.basketstat.screen.player.PlayerBrowse;
import com.company.basketstat.screen.team.TeamBrowse;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.ScreenTools;
import io.jmix.ui.component.AppWorkArea;
import io.jmix.ui.component.Window;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("MainTop")
@UiDescriptor("main-screen-top-menu.xml")
@Route(path = "main", root = true)
public class MainScreenTopMenu extends Screen implements Window.HasWorkArea {

    @Autowired
    private ScreenTools screenTools;

    @Autowired
    private AppWorkArea workArea;
    @Autowired
    private ScreenBuilders screenBuilders;

    @Override
    public AppWorkArea getWorkArea() {
        return workArea;
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        screenTools.openDefaultScreen(
                UiControllerUtils.getScreenContext(this).getScreens());
        screenTools.handleRedirect();
        screenBuilders.screen(this)
                .withScreenClass(GameBrowse.class)
                .withOpenMode(OpenMode.NEW_TAB)
                .build()
                .show();
        screenBuilders.screen(this)
                .withScreenClass(TeamBrowse.class)
                .withOpenMode(OpenMode.NEW_TAB)
                .build()
                .show();
        screenBuilders
                .screen(this)
                .withOpenMode(OpenMode.NEW_TAB)
                .withScreenClass(PlayerBrowse.class)
                .build()
                .show();
    }
}