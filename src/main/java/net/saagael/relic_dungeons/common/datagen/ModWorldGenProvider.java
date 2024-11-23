package net.saagael.relic_dungeons.common.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.saagael.relic_dungeons.RelicDungeons;
import net.saagael.relic_dungeons.common.world.biomes.SeaOfStarsBiome;
import net.saagael.relic_dungeons.common.world.dimensions.SeaOfStars;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.BIOME, SeaOfStarsBiome::bootstrapSeaOfStarsBiome)
            .add(Registries.DIMENSION_TYPE, SeaOfStars::bootstrapSeaOfStarsDimensionType)
            .add(Registries.LEVEL_STEM, SeaOfStars::boostrapSeaOfStarsStem);

    public ModWorldGenProvider(
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider
    ) {
        super(packOutput, lookupProvider, BUILDER, Set.of(RelicDungeons.MOD_ID));
    }
}
