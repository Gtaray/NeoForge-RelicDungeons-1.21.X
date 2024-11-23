package net.saagael.relic_dungeons.registers;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.tiles.RelicAltarTile;
import net.saagael.relic_dungeons.common.tiles.RelicLocatorTile;

import java.util.function.Supplier;

public class RelicDungeonTiles
{
    public static final DeferredRegister<BlockEntityType<?>> TILES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, RelicDungeons.MOD_ID);

    public static final Supplier<BlockEntityType<RelicLocatorTile>> RELIC_LOCATOR_TILE = TILES.register(
            "relic_locator_entity",
            () -> BlockEntityType.Builder.of(
                            RelicLocatorTile::new,
                            RelicDungeonBlocks.RELIC_LOCATOR_BLOCK.get())
                    .build(null)
    );
    public static final Supplier<BlockEntityType<RelicAltarTile>> RELIC_ALTAR_TILE = TILES.register(
            "relic_altar_entity",
            () -> BlockEntityType.Builder.of(
                    RelicAltarTile::new,
                    RelicDungeonBlocks.RELIC_ALTAR_BLOCK.get())
                .build(null)
    );

    public static void register(IEventBus eventBus)
    {
        TILES.register(eventBus);
    }
}
