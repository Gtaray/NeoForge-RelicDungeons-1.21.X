package net.saagael.relic_dungeons.common.templates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.ArrayList;
import java.util.List;

public class ThemeTemplate {
    public static final Codec<ThemeTemplate> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    RoomTemplate.CODEC.listOf().fieldOf("starting_rooms").forGetter((ThemeTemplate t) -> t.starting_rooms)
            ).apply(instance, ThemeTemplate::new)
    );

    List<RoomTemplate> starting_rooms = new ArrayList<>();

    public ThemeTemplate(List<RoomTemplate> starts) {
        this.starting_rooms = starts;
    }
}
