package com.company.basketstat.screen.team;

import com.company.basketstat.entity.Player;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.component.TagPicker;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.basketstat.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("bst_Team.edit")
@UiDescriptor("team-edit.xml")
@EditedEntityContainer("teamDc")
public class TeamEdit extends StandardEditor<Team> {
}