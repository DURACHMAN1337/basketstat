package com.company.basketstat.entity;

import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@JmixEntity
@Entity(name = "bst_Team")
public class Team extends BaseUUIDEntity {
    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @InstanceName
    @Column(name = "NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> player) {
        this.players = player;
    }
}