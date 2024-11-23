package net.saagael.relic_dungeons.common.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Rotation;

public enum DungeonDifficulty implements StringRepresentable {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD"),
    DEADLY("DEADLY");

    public static final Codec<DungeonDifficulty> CODEC = StringRepresentable.fromEnum(DungeonDifficulty::values);
    private final String id;

    DungeonDifficulty(String id) {
        this.id = id;
    }

    @Override
    public String getSerializedName() {
        return this.id;
    }
}
