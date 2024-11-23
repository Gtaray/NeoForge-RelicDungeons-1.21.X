package net.saagael.relic_dungeons.common.templates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.saagael.relic_dungeons.common.util.Coords;
import net.saagael.relic_dungeons.common.util.ExitDirection;

import java.util.ArrayList;
import java.util.List;

public class CellTemplate {
    public static final Codec<CellTemplate> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Coords.CODEC.fieldOf("coordinates").forGetter((CellTemplate c) -> c.coordinates),
                    CellStructureTemplate.CODEC.listOf().fieldOf("variants").forGetter((CellTemplate c) -> c.cell_variants)
            ).apply(instance, CellTemplate::new)
    );

    Coords coordinates; // Relative to the origin of the room.
    List<CellStructureTemplate> cell_variants = new ArrayList<>();

    public CellTemplate() {

    }

    public CellTemplate(Coords coordinates, List<CellStructureTemplate> variants) {
        this.coordinates = coordinates;
        this.cell_variants = variants;
    }
}
