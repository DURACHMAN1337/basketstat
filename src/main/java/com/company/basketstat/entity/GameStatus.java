package com.company.basketstat.entity;

import io.jmix.core.metamodel.datatype.impl.EnumClass;

import javax.annotation.Nullable;


public enum GameStatus implements EnumClass<String> {

    STARTED("STARTED"),
    FINISHED("FINISHED");

    private String id;

    GameStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static GameStatus fromId(String id) {
        for (GameStatus at : GameStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}