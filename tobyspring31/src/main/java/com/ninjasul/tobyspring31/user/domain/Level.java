package com.ninjasul.tobyspring31.user.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum Level {
    BASIC(1),
    SILVER(2),
    GOLD(3);

    private static final Map<Integer, Level> userLevelMap;

    static {
        initNext();
        userLevelMap = createUserLevelMap();
    }

    private static void initNext() {
        Level.BASIC.next = Level.SILVER;
        Level.SILVER.next = Level.GOLD;
        Level.GOLD.next = Level.GOLD;
    }

    private static Map<Integer, Level> createUserLevelMap() {
        Map<Integer, Level> map = new HashMap<>();

        for( Level level : values() ) {
            map.put(level.value, level);
        }

        return map;
    }

    private final int value;

    private Level next;

    Level(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public static Level valueOf(int value) {
        return userLevelMap.get(value);
    }

    public Level nextLevel() {
        return next;
    }
}
