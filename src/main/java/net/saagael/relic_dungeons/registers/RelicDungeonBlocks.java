package net.saagael.relic_dungeons.registers;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.blocks.RelicAltarBlock;
import net.saagael.relic_dungeons.common.blocks.RelicLocatorBlock;

import java.util.function.Supplier;

public class RelicDungeonBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RelicDungeons.MOD_ID);

    public static final DeferredBlock<Block> RELIC_ALTAR_BLOCK = registerBlock(
            "relic_altar",
            () -> new RelicAltarBlock(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final DeferredBlock<RelicLocatorBlock> RELIC_LOCATOR_BLOCK = registerBlock(
            "relic_locator",
            () -> new RelicLocatorBlock(
                    BlockBehaviour.Properties.of()
                            .strength(4, 1000F)
                            .requiresCorrectToolForDrops()
            )
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block)
    {
        DeferredBlock<T> result = BLOCKS.register(name, block);
        registerBlockItem(name, result);
        return result;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block)
    {
        RelicDungeonItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
