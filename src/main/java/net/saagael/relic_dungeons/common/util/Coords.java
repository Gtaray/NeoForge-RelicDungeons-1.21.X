package net.saagael.relic_dungeons.common.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.saagael.relic_dungeons.common.templates.CellTemplate;
import org.joml.Vector2d;
import org.joml.Vector2i;

public class Coords extends Vector2i {
    public static final Codec<Coords> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.orElse(0).fieldOf("x").forGetter((Coords c) -> c.x),
                    Codec.INT.orElse(0).fieldOf("y").forGetter((Coords c) -> c.y)
            ).apply(instance, Coords::new)
    );

    public Coords(int x, int y) {
        super(x, y);
    }

    public Coords() {
    }
}
