package net.saagael.relic_dungeons.common.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.joml.Vector2i;

public class Size extends Vector2i {
    public static final Codec<Size> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.orElse(1).fieldOf("h").forGetter((Size s) -> s.x),
                    Codec.INT.orElse(1).fieldOf("w").forGetter((Size s) -> s.y)
            ).apply(instance, Size::new)
    );

    public Size(int width, int height) {
        super(width, height);
    }

    public Size() {
    }
}
