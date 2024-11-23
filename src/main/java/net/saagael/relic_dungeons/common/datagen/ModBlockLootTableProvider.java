package net.saagael.relic_dungeons.common.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.saagael.relic_dungeons.registers.RelicDungeonBlocks;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider
{
    protected ModBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(RelicDungeonBlocks.RELIC_ALTAR_BLOCK.get());
        dropSelf(RelicDungeonBlocks.RELIC_LOCATOR_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
         return RelicDungeonBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
