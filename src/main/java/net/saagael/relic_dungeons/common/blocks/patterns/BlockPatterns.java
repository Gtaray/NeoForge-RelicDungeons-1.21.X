package net.saagael.relic_dungeons.common.blocks.patterns;

import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;

public class BlockPatterns {
    public static final BlockPattern RELIC_PORTAL = BlockPatternBuilder.start()
            .aisle("*")
            .where('*', BlockInWorld.hasState(BlockStatePredicate.ANY))
            .build();
}
