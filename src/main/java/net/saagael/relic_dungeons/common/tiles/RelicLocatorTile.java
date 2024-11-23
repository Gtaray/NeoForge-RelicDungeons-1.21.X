package net.saagael.relic_dungeons.common.tiles;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.saagael.relic_dungeons.registers.RelicDungeonTiles;

public class RelicLocatorTile extends BlockEntity {
    public static float ROTATION_SPEED = 0.15f;
    public static float BOB_HEIGHT = 0.07f;
    public static float BOBS_PER_SECOND = 0.15f;

    public float bob_timer = 0;
    public float rotation_timer = 0;

    public RelicLocatorTile(BlockPos pos, BlockState blockState) {
        super(RelicDungeonTiles.RELIC_LOCATOR_TILE.get(), pos, blockState);
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, RelicLocatorTile tile) {
        // 18 degrees per tick is the default rate to get 1 full cycle per second
        tile.bob_timer += 18f * BOBS_PER_SECOND;
        tile.bob_timer %= 360; // Lock this timer between 0 and 359

        tile.rotation_timer += 18f * ROTATION_SPEED;
        tile.rotation_timer %= 360f;
    }

    public float getRenderingHeight(float partialTick) {
        float timer = bob_timer + partialTick;
        return Mth.sin((float)Math.toRadians(timer)) * BOB_HEIGHT;
    }
}
