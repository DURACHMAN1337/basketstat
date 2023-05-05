package com.company.basketstat.screen.team;

import io.jmix.ui.screen.*;
import com.company.basketstat.entity.Team;

@UiController("bst_Team.browse")
@UiDescriptor("team-browse.xml")
@LookupComponent("teamsTable")
public class TeamBrowse extends StandardLookup<Team> {
}