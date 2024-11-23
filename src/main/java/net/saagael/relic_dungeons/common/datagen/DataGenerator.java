package net.saagael.relic_dungeons.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.saagael.relic_dungeons.RelicDungeons;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = RelicDungeons.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerator
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        net.minecraft.data.DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Generate Recipes
        generator.addProvider(
                event.includeServer(),
                new ModRecipeProvider(output, lookupProvider));

        // Generate Loot Tables
        generator.addProvider(
                event.includeServer(),
                new LootTableProvider(
                        output,
                        Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                ModBlockLootTableProvider::new,
                                LootContextParamSets.BLOCK)),
                        lookupProvider));

        // Generate Tags
        BlockTagsProvider blockTagsProvider = new ModBlockTagProvider(output, lookupProvider, fileHelper);
        generator.addProvider(
                event.includeServer(),
                blockTagsProvider);
        generator.addProvider(
                event.includeServer(),
                new ModItemTagProvider(
                        output,
                        lookupProvider,
                        blockTagsProvider.contentsGetter(),
                        fileHelper));

        // Generator Item Models
        generator.addProvider(
                event.includeClient(),
                new ModItemModelProvider(
                        output,
                        fileHelper));

        // Generate Block States
        generator.addProvider(
                event.includeClient(),
                new ModBlockStateProvider(
                        output,
                        fileHelper));

        // Generate World Gen
        generator.addProvider(
                event.includeServer(),
                new ModWorldGenProvider(output, lookupProvider));
    }
}
