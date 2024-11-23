package net.saagael.relic_dungeons.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.client.renderer.tile.RenderRelicAltar;
import net.saagael.relic_dungeons.client.renderer.tile.RenderRelicLocator;
import net.saagael.relic_dungeons.registers.RelicDungeonTiles;

@EventBusSubscriber(modid = RelicDungeons.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegistration {
    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(
                RelicDungeonTiles.RELIC_LOCATOR_TILE.get(),
                RenderRelicLocator::new
        );
        event.registerBlockEntityRenderer(
                RelicDungeonTiles.RELIC_ALTAR_TILE.get(),
                RenderRelicAltar::new
        );
    }

    @SubscribeEvent
    public static void registerModels(ModelEvent.RegisterAdditional event) {
//            event.register(new ModelResourceLocation(
//                    // The id of the model
//                    ResourceLocation.fromNamespaceAndPath(RelicDungeons.MOD_ID, "block/relic_locator/relic_locator_cube"),
//                    // The string representing what variant of the model this is for
//                    // In normal vanilla, this would be one of three values:
//                    // - Blocks: The stringified block state
//                    // - Items: 'inventory' as it is the inventory model
//                    // - Standalone: 'standalone' as this does not refer to any other model
//                    "variant_type=true"
//            ));

        // A standalone model example
        event.register(RenderRelicLocator.LOCATOR_CUBE_MODEL);
    }
}
