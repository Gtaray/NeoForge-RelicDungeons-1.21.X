package net.saagael.relic_dungeons.client.renderer.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.tiles.RelicLocatorTile;
import org.joml.Quaternionf;

public class RenderRelicLocator implements BlockEntityRenderer<RelicLocatorTile> {
    public static final ModelResourceLocation LOCATOR_CUBE_MODEL = ModelResourceLocation.standalone(
            ResourceLocation.fromNamespaceAndPath(RelicDungeons.MOD_ID, "block/relic_locator_cube")
    );

    public RenderRelicLocator(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(
            RelicLocatorTile tile,
            float partialTick,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            int packedOverlay) {

        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        BakedModel model = Minecraft
                .getInstance()
                .getModelManager()
                .getModel(LOCATOR_CUBE_MODEL);
        Level level = tile.getLevel();

        poseStack.pushPose();

        // Change the height
        poseStack.translate(0.3125f, 1.1f + tile.getRenderingHeight(partialTick), 0.3125f);

        // Handle rotation
        float rotation = (float) Math.toRadians(tile.rotation_timer + partialTick);
        // Offset by 3 pixels so the rotation happens within the center of the cube
        poseStack.translate(0.1875, 0.1875, 0.1875);
        poseStack.mulPose(new Quaternionf().rotationXYZ(rotation, 0, rotation));
        // Return the cube back to its normal spot after rotating.
        poseStack.translate(-0.1875, -0.1875, -0.1875);

        // push to the render stack
        renderer.renderModelLists(
                model,
                ItemStack.EMPTY,
                getLightLevel(level, tile.getBlockPos()),
                packedOverlay,
                poseStack,
                ItemRenderer.getFoilBufferDirect(
                        bufferSource,
                        Sheets.translucentItemSheet(),
                        true,
                        false));

        poseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos)
    {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight =  level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, skyLight);
    }
}
