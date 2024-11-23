package net.saagael.relic_dungeons.common.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum ExitDirection implements StringRepresentable {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    public static final Codec<ExitDirection> CODEC = StringRepresentable.fromEnum(ExitDirection::values);
    private final String id;

    ExitDirection(String id) {
        this.id = id;
    }

    @Override
    public String getSerializedName() {
        return this.id;
    }
}
