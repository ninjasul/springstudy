package com.ninjasul.tobyspring31.user.domain;

import java.util.Arrays;
import java.util.List;

public enum Level {
    BASIC,
    SILVER,
    GOLD;

    private static final List<Level> userLevelList = Arrays.asList(Level.values());

    public int intValue() {
        return ordinal()+1;
    }

    public static Level valueOf(int value) {
        if( value <= 0 || value > userLevelList.size() )
            throw new AssertionError("Unknown value: " + value);

        return userLevelList.get(value-1);
    }
}
