package net.saagael.relic_dungeons.common.templates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.saagael.relic_dungeons.common.util.Coords;
import net.saagael.relic_dungeons.common.util.DungeonDifficulty;
import net.saagael.relic_dungeons.common.util.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomTemplate {
    public static final Codec<RoomTemplate> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Size.CODEC.fieldOf("size").forGetter((RoomTemplate r) -> r.size),
                    DungeonDifficulty.CODEC.listOf().fieldOf("difficulty").forGetter((RoomTemplate r) -> r.difficulties),
                    CellTemplate.CODEC.listOf().fieldOf("cells").forGetter((RoomTemplate r) -> r.cells)
            ).apply(instance, RoomTemplate::new)
    );

    Size size;
    List<DungeonDifficulty> difficulties = new ArrayList<>();
    List<CellTemplate> cells = new ArrayList<>();

    public RoomTemplate() {

    }

    public RoomTemplate(Size size, List<DungeonDifficulty> difficulty, List<CellTemplate> cells) {
        this.size = size;
        this.difficulties = difficulty;
        this.cells = cells;
    }

    public boolean isDifficulty(DungeonDifficulty difficulty) {
        return difficulties.contains(difficulty);
    }

}
