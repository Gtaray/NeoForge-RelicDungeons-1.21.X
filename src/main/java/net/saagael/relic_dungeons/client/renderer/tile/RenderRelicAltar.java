package net.saagael.relic_dungeons.client.renderer.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.saagael.relic_dungeons.common.tiles.RelicAltarTile;

public class RenderRelicAltar implements BlockEntityRenderer<RelicAltarTile> {
    public RenderRelicAltar(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(RelicAltarTile tile, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = tile.getItem(0);
        Level level = tile.getLevel();

        poseStack.pushPose();

        poseStack.translate(0.5f, 1.1f + tile.getRenderingHeight(), 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(tile.getRenderingRotation()));
        renderer.renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                getLightLevel(level, tile.getBlockPos()),
                OverlayTexture.NO_OVERLAY,
                poseStack,
                bufferSource,
                level,
                1
        );

        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight =  level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
