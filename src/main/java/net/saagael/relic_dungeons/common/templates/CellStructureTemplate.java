package net.saagael.relic_dungeons.common.templates;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.util.ExitDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CellStructureTemplate {
    public static final Codec<CellStructureTemplate> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    ResourceLocation.CODEC.fieldOf("structure").forGetter((CellStructureTemplate c) -> c.structure),
                    ExitDirection.CODEC.listOf().fieldOf("exits").forGetter((CellStructureTemplate c) -> c.exits)
            ).apply(instance, CellStructureTemplate::new)
    );

    public ResourceLocation structure;
    public List<ExitDirection> exits = new ArrayList<>();

    public CellStructureTemplate() {

    }

    public CellStructureTemplate(ResourceLocation structure, List<ExitDirection> exits) {
        this.structure = structure;
        this.exits = exits;
    }
}
